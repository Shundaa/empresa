package com.qa.app.service.impl;

import com.qa.app.dao.response.LeaderDao;
import com.qa.app.repository.UserRepository;
import com.qa.app.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LeaderDao> getTop5() {
        return userRepository.findTop5ByOrderByBugDesc().
                stream()
                .map(user -> modelMapper.map(user, LeaderDao.class))
                .toList();
    }
}
