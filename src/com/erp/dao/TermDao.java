package com.erp.dao;


import com.erp.pojo.Terms;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Minal
 * Date: Apr 25, 2012
 * Time: 1:14:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TermDao {
     public boolean SaveTerm(Terms terms);
    public Terms findByMaxId();
      public Terms findById(Long id);
}
