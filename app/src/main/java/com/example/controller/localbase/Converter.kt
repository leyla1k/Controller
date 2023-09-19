package com.example.controller.localbase

import com.example.controller.entities.Card
import com.example.controller.entities.CardDataItem
import com.example.controller.entities.Gender
import com.example.controller.entities.Path
import com.example.controller.entities.PathDataItem
import com.example.controller.entities.WorkShift
import com.example.controller.entities.WorkShiftDataItem
import java.util.Date


fun fromTimestampToDate(value: Long?): Date? {
    return value?.let { Date(it) }
}

fun fromDateToTimestamp(date: Date?): Long? {
    return date?.time?.toLong()
}

fun fromBooleanToGender(value: Boolean): Gender {
    if (value) {
        return Gender.FEMALE
    } else {
        return Gender.MALE
    }
}

fun fromCardDataItemToCard(cardDataItem: CardDataItem?): Card? {
    return if (cardDataItem != null) {
        Card(
            id = cardDataItem.id,
            tagId = cardDataItem.tagId,
            name = cardDataItem.name,
            surName = cardDataItem.surName,
            patronymic = cardDataItem.patronymic,
            dateOfBirth = fromTimestampToDate(cardDataItem.dateOfBirth)!!,
            gender = fromBooleanToGender(cardDataItem.gender)
        )
    } else null
}

fun fromPathDataItemToPath(pathDataItem: PathDataItem): Path {
    return Path(
        id = pathDataItem.id,
        workId = pathDataItem.workId,
        govNumber = pathDataItem.govNumber,
        pathNumber = pathDataItem.pathNumber,
        //userId = pathDataItem.userId,
        startDate = fromTimestampToDate(pathDataItem.startDate)!!,
        endDate = fromTimestampToDate(pathDataItem.endDate)
    )

}

fun fromPathToPathDataItem(path: Path): PathDataItem {
    return PathDataItem(
        id = path.id,
        workId = path.workId,
        govNumber = path.govNumber,
        pathNumber = path.pathNumber,
        //userId = path.userId,
        startDate = fromDateToTimestamp(path.startDate)!!,
        endDate = path.endDate?.let { fromDateToTimestamp(it) }
    )

}

fun fromWorkShiftToWorkShiftDataItem(workShift: WorkShift): WorkShiftDataItem {
    return WorkShiftDataItem(
        id = workShift.id,
        startDate = fromDateToTimestamp(workShift.startDate)!!,
        endDate = workShift.endDate?.let { fromDateToTimestamp(it) }
    )
}

fun fromWorkShiftDataItemToWorkShift(workShift: WorkShiftDataItem?): WorkShift? {
    return workShift?.let {
        WorkShift(
            id = it.id,
            startDate = fromTimestampToDate(workShift.startDate)!!,
            endDate = fromTimestampToDate(workShift.endDate)
        )
    }
}