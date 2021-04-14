/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

public enum FControl{
    getAll(0),
    getByID(1),
    getCrudState(2),
    setDAO(3),
    setDml(4);
    
    private final int value;

    FControl(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}