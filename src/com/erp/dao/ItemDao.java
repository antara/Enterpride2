package com.erp.dao;

import com.erp.pojo.Item;
import com.erp.pojo.Section;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Feb 14, 2012
 * Time: 12:28:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao {

    public boolean SaveItem(Item item);

    public List getItem();

    public void delete(Item item);

    public void update(Item item);

    public Item findById(Long id);

    public List getItemId();

    public List getItemCode();

    public List<Item> searchByName(String name);

    public List<String> allItem();

    public List<String> getItemCodelst();
    public List<String> getItemNamelst();

    public boolean checkUomPresent(String addUomName);

    public boolean checkItemPresent(String addItemName);

    public List<String> getItemUomlst();

    public List<String> getItemSectionlst();

    public boolean checkSectionPresent(String addSectionName);

    public List<Item> searchByItemCode(String name);

    public List<Item> searchByItemUom(String name);

    public List<Item> searchByItemSection(String name);
}

