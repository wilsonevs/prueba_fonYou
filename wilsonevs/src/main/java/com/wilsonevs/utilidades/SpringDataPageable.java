package com.wilsonevs.utilidades;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class SpringDataPageable implements Serializable, Pageable {

    private static final long serialVersionUID = 1;
    // página actual
    private Integer pagenumber = 1;
    // Número de página actual
    private Integer pagesize = 10;
    // Ordenar condición
    private Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    // La página actual
    @Override
    public int getPageNumber() {
        return getPagenumber();
    }

    // Número de elementos que se muestran en cada página
    @Override
    public int getPageSize() {
        return getPagesize();
    }

    // El número de páginas adicionales necesarias
    @Override
    public long getOffset() {
        return (getPagenumber() - 1) * getPagesize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    @Override
    public Pageable next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasPrevious() {
        // TODO Auto-generated method stub
        return false;
    }
}