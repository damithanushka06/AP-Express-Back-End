package com.ap_express_server.repository.item;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.item.Item;
import com.ap_express_server.models.item.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /**
     * Retrieves a list of all items with their associated unit of measure (UOM) and category names.
     *
     * @return a list of ItemDto objects containing the item information
     */
    @Query("SELECT NEW com.ap_express_server.models.item.ItemDto(item.id, item.name, item.description," +
            " cat.name, uom.name, item.unitPrice, item.createdBy, item.createdDate)" +
            " FROM Item item JOIN UOM uom ON uom.id = item.uomId JOIN Category cat ON cat.id = item.categoryId")
    List<ItemDto> getAllItemList();

    /**
     * Retrieves a list of items in a dropdown format.
     *
     * @return a list of DropDownDto objects containing the item's ID and name
     */
    @Query("SELECT NEW com.ap_express_server.models.dropdown.DropDownDto(item.id, item.number) FROM Item item")
    List<DropDownDto> getItemDropDownList();
}
