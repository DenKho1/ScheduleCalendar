package com.mobdeve.s13.kho.denise.schedulecalendar

class HostInviteGenerator {

    companion object {
        fun genData(): ArrayList<HostInvite> {
            var tempList =  ArrayList<HostInvite>()
            tempList.add(HostInvite("Will"))
            tempList.add(HostInvite("John"))
            tempList.add(HostInvite("Bill"))
            tempList.add(HostInvite("Mikel"))
            tempList.add(HostInvite("Carmelo"))
            return tempList
        }
    }
}