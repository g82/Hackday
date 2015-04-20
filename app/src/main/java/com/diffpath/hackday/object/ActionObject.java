package com.diffpath.hackday.object;

/**
 * Created by Heedeok on 15. 4. 20..
 */
public class ActionObject {
    private int _id;
    private String _name;
    private String _description;
    private int _img;

    public ActionObject(){
        _id = 0;
        _name = "";
        _description = "";
    }

    public void setId(int val)
    {
        _id = val;
    }

    public int getId()
    {
        return _id;
    }

    public void setName(String val)
    {
        _name = val;
    }

    public String getName()
    {
        return _name;
    }

    public void setDescription(String val)
    {
        _description = val;
    }

    public String getDescription()
    {
        return _description;
    }

    public void setImg(int val)
    {
        _img = val;
    }

    public int getImg()
    {
        return _img;
    }
}
