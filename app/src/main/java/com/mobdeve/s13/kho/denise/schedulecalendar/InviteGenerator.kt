package com.mobdeve.s13.kho.denise.schedulecalendar

class InviteGenerator {
        companion object{
            fun genData(): ArrayList<Invite> {
                var tempList = ArrayList<Invite>()
                tempList.add(Invite("Will", "Basketball", "August 31, 2022"))
                tempList.add(Invite("John", "Wedding", "September 10, 2022"))
                tempList.add(Invite("Bill", "Honeymoon", "September 13, 2022"))
                tempList.add(Invite("Mikel", "Vacation Trip", "September 20, 2022"))
                tempList.add(Invite("Carmelo", "Vacation Trip 2", "October 5, 2022"))
                return tempList
            }
        }
    }
