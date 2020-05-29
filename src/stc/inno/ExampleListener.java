package stc.inno;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class ExampleListener implements RowSetListener {
  @Override public void rowSetChanged(RowSetEvent event) {
    System.out.println("rowSetChanged");
    System.out.println(event.getSource());
  }

  @Override public void rowChanged(RowSetEvent event) {
    System.out.println("rowChanged");
    System.out.println(event.getSource());
  }

  @Override public void cursorMoved(RowSetEvent event) {
    System.out.println("cursorMoved");
    System.out.println(event.getSource());
  }
}
