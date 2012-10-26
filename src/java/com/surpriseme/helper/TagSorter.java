/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.helper;

import java.util.Comparator;

/**
 *
 * @author ankur
 */
public class TagSorter implements Comparator<TagHelper> {

    @Override
    public int compare(TagHelper o1, TagHelper o2) {
        return o2.getCounts() - o1.getCounts();
    }
}
