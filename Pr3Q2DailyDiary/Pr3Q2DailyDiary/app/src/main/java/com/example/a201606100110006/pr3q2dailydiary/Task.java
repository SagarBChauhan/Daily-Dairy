package com.example.a201606100110006.pr3q2dailydiary;

public class Task {
    int _ID;
    String _DATE;
    String _TIME;
    String _DESCRIPTION;

    public Task()
    {

    }
    public Task(int Id)
    {
        this._ID = Id;
    }
    public Task(String DATE,String TIME,String DESCRIPTION)
    {
        this._DATE=DATE;
        this._TIME=TIME;
        this._DESCRIPTION=DESCRIPTION;
    }
    public Task(int ID,String DATE,String TIME,String DESCRIPTION)
    {
        this._ID=ID;
        this._DATE=DATE;
        this._TIME=TIME;
        this._DESCRIPTION=DESCRIPTION;
    }
    public int getID()
    {
        return this._ID;
    }
    public  void setID(int ID)
    {
        this._ID=ID;
    }
    public String getDate()
    {
        return this._DATE;
    }
    public void setDate(String DATE)
    {
        this._DATE=DATE;
    }
    public String getTime()
    {
        return this._TIME;
    }
    public  void setTime(String TIME)
    {
        this._TIME=TIME;
    }
    public String getDescription()
    {
        return this._DESCRIPTION;
    }
    public  void setDescription(String DESCRIPTION)
    {
        this._DESCRIPTION=DESCRIPTION;
    }

}
