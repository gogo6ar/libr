package com.isd.libr.service;


import com.isd.libr.web.dto.requests.AddVoteRequest;

public interface VoteService {
    void vote(AddVoteRequest request) throws RepeatedVoteException;
}