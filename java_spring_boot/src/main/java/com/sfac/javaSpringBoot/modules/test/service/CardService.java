package com.sfac.javaSpringBoot.modules.test.service;

import com.sfac.javaSpringBoot.modules.commo.vo.Result;
import com.sfac.javaSpringBoot.modules.test.entity.Card;

public interface CardService {
    Result<Card> insertCard(Card card);

}
