package com.qa.app.service.impl;

import com.qa.app.dao.response.UserDao;
import com.qa.app.entities.User;
import com.qa.app.repository.UserRepository;
import com.qa.app.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDao getStatistics(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return modelMapper.map(user.get(), UserDao.class);
    }

    @Override
    public UserDao addCoin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setCoin(user.getCoin() + 1);
        return modelMapper.map(userRepository.save(user), UserDao.class);
    }

    @Override
    public UserDao addRefill(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setRefill(user.getRefill() + 1);
        return modelMapper.map(userRepository.save(user), UserDao.class);
    }

    @Override
    public UserDao addVictory(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setVictory(user.getVictory() + 1);
        return modelMapper.map(userRepository.save(user), UserDao.class);
    }

    @Override
    public UserDao addBug(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setBug(user.getBug() + 1);
        return modelMapper.map(userRepository.save(user), UserDao.class);
    }
}
