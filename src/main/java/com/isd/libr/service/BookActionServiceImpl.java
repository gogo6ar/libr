package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookActionInfoDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class BookActionServiceImpl implements BookActionService {
    private final BookActionRepository bookActionRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<BookAction> getByStatus(Status status) {
        return bookActionRepository.getAllByStatus(status);
    }

    @Override
    @Transactional
    public BookActionDto updateStatus(UpdateBooksStatusRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", request.getUserId()));
        }
        Optional<Book> book = bookRepository.findById(request.getBookId());
        if (book.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with ID [%s] not found", request.getBookId()));
        }
        BookAction bookAction = new BookAction(user.get(), book.get(), LocalDateTime.now(), Status.valueOf(request.getNewStatus()));
        BookAction updatedBookAction = bookActionRepository.save(bookAction);
        UserDto userDto = UserDto.from(updatedBookAction.getUser());
        BookDto bookDto = BookDto.from(updatedBookAction.getBook());
        return BookActionDto.from(updatedBookAction, userDto, bookDto);
    }

     public BookActionInfoDto getInfo(Long id){
        BookAction bookAction = bookActionRepository.findLastActionByBookId(id);
        String userFirstName = bookAction.getUser().getFirstName();
        String userLastName = bookAction.getUser().getLastName();
        String status = bookAction.getStatus().toString();
        String date = bookAction.getActionDate().format(DateTimeFormatter.ofPattern("yyyy MM dd HH mm"));
        return BookActionInfoDto.from(userFirstName,userLastName,status,date);
    }
     }




