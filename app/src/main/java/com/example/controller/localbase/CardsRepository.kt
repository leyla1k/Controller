package com.example.controller.localbase

import com.example.controller.entities.Card
import com.example.controller.entities.CardDataItem
import com.example.controller.entities.Gender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class CardsRepository(val dao: MainDao) {

     fun getCardByTagId(tagId: String): Card? {
        return fromCardDataItemToCard(dao.getCardByTagId(tagId))
    }

}