package com.example.findcompanyAPI.Models;

import java.io.Serializable;

public class ConfirmVisit implements Serializable {

//    public ConfirmVisit(int id, int id_event, int id_user,int id_creator, String name_event, String place_event, String data_and_time_event ,
//                        int max_participants_event , String surname ) {
//        this.surname  = surname;
//        this.id = id;
//        this.id_event = id_event;
//        this.id_creator = id_creator;
//        this.id_user = id_user;
//        this.place_event = place_event;
//        this.name_event = name_event;
//        this.data_and_time_event  = data_and_time_event ;
//        this.max_participants_event  = max_participants_event ;
//    }

    private int id;

    public ConfirmVisit(String id_event, String id_creator, String id_user, String name_event, String place_event, String data_and_time_event,
                        String max_participants_event, String surname) {
        this.id_event = id_event;
        this.id_creator = id_creator;
        this.id_user = id_user;
        this.name_event = name_event;
        this.place_event = place_event;
        this.data_and_time_event  = data_and_time_event ;
        this.max_participants_event  = max_participants_event ;
        this.surname  = surname;
    }

    public String getId(){ return Integer.toString(id);}

    private String id_event;
    public String getId_event(){ return id_event;}

    private String id_user;
    public String getId_user(){ return id_user;}

    private String id_creator;
    public String getId_creator(){ return id_creator;}
    //
    private String name_event;
    public String getName_event(){
        return this.name_event;
    }
    public void setName_event(String info){
        this.name_event = info;
    }
    //
    private String place_event;
    public String getPlace_event(){
        return place_event;
    }
    public void setPlace_event(String info){
        this.place_event = info;
    }
    //
    private String data_and_time_event ;
    public String getDataAndtime_event(){
        return data_and_time_event ;
    }
    public void setDataAndtime_event(String info){
        this.data_and_time_event  = info;
    }
    //
    private String max_participants_event ;
    public String getMaxParticipants_event(){ return max_participants_event;}

    private String surname ;
    public String getSurname(){
        return surname ;
    }
    public void setSurname(String info){
        this.surname  = info;
    }


}
