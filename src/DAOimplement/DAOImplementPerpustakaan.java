
package DAOimplement;

import java.util.List;
import model.DataPerpustakaan;


/**
 *
 * @author HP
 */
public interface DAOImplementPerpustakaan {
    public void insert(DataPerpustakaan dp);
    public void update(DataPerpustakaan dp);
    public void delete(Integer Id);
    public List<DataPerpustakaan>getAll();
}
