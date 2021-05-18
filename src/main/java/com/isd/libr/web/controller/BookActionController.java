package com.isd.libr.web.controller;

import com.isd.libr.service.BookActionService;
import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.dto.requests.RequestBookRequest;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-actions")
public class BookActionController {
    private final BookActionService bookActionService;

    @GetMapping("/{status}")
    public ResponseEntity<?> getSubmittedBooks(@PathVariable Status status) {
        List<BookAction> actions = bookActionService.getByStatus(status);
        return ResponseEntity.ok(actions);
    }

    @PostMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateBooksStatusRequest request) {
        BookActionDto updatedBookAction = bookActionService.updateStatus(request);
        return ResponseEntity.ok(updatedBookAction);
    }

}