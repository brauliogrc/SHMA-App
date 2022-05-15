/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.ICRUD;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public interface ICRUD<T> {
  public boolean add( T t );
  public boolean update( T t );
  public boolean delete( T t );
  public T getOne( int id );
  public ArrayList<T> getAll();
  public ArrayList<T> query( T t );
}
