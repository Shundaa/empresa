package com.qa.app.service;

import com.qa.app.dao.response.UserDao;

public interface StatisticsService {
    UserDao addCoin(Long id);

    UserDao addRefill(Long id);

    UserDao addVictory(Long id);

    UserDao getStatistics(Long id);

    UserDao addBug(Long id);
}
