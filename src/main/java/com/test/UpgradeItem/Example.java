package com.test.UpgradeItem;

import java.util.List;

public class Example {
    protected String FirstItemLore;
    protected String ItemName;
    protected List<String> GradeItemLore;
    protected List<String>  ReplaceLore;
    protected int Success;
    public Example(String lore ,String name ,List<String> itemLore,List<String> replaceLore,int Success){
        this.FirstItemLore =lore;
        this.ItemName=name;
        this.GradeItemLore=itemLore;
        this.ReplaceLore=replaceLore;
        this.Success=Success;
    }
    public String getFirstItemLore(){
        return this.FirstItemLore;
    }
    public String getItemName(){
        return this.ItemName;
    }
    public List<String> getGradeItemLore(){
        return this.GradeItemLore;
    }
    public List<String> getReplaceLore(){
        return this.ReplaceLore;
    }
    public int getSuccess(){return this.Success;}
}
