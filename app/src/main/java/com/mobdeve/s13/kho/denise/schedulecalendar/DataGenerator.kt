package com.mobdeve.s13.kho.denise.schedulecalendar

class DataGenerator {
    companion object{
        fun generateData(): ArrayList<Event> {
            var tempList = ArrayList<Event>()
            tempList.add(Event("Basketball", "Madison Square Garden", "August 31, 2022"))
            tempList.add(Event("Wedding", "Cebu", "September 10, 2022"))
            tempList.add(Event("Honeymoon", "Siargao", "September 13, 2022"))
            tempList.add(Event("Vacation Trip", "Japan", "September 20, 2022"))
            tempList.add(Event("Vacation Trip 2", "Korea", "October 5, 2022"))
            return tempList
        }
    }
}