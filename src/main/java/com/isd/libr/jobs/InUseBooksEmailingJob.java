package com.isd.libr.jobs;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class InUseBooksEmailingJob {
    private final BookActionRepository bookActionRepository;
    private final EmailService emailService;
    @Value("${status.in.use.age}")
    private Integer inUseDaysAge;
    @Value("${spring.mail.username}")
    private String from;

    @Scheduled(cron = "${status.in.use.email.frequency}")
    private void checkIfStatusInUseIsMoreDays() {
        String[] allEmails = bookActionRepository.findAllInUseOlderThen(inUseDaysAge)
                .stream().map(ba -> ba.getUser().getEmail())
                .toArray(String[]::new);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(allEmails);
        message.setFrom(from);
        message.setSubject("Notification in use message");
        message.setText("Give back the book");
        emailService.sendSimpleMessage(message);
    }
}