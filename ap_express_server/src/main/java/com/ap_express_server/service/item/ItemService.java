package com.ap_express_server.service.item;
import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.item.Item;
import com.ap_express_server.models.item.ItemDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {

    /**
     * this service used to create item
     * @param item to Item reference
     * @return to the service impl
     */
    Item createItem(Item item);

    /**
     * this service used to load category list
     * @return to the service impl
     */
    List<DropDownDto> getCategoryList();

    /**
     * this service used to load uom list
     * @return to the service impl
     */
    List<DropDownDto> getUOMList();

    /**
     * this service used to load all item list
     * @return to the service impl
     */
    List<ItemDto> getAllItemList();

    /**
     * this service used to load item details by item id
     * @return to the service impl
     */
    Optional<Item> getItemDetailById(Integer itemId);


    /**
     * this service used to delete item
     * @param itemId to item master id
     */
    void deleteItemDetailById(Integer itemId);

    /**
     * this service used to update item
     * @param item to item master object
     */
    Item updateItem(Item item);

    /**
     * Retrieves a list of items in a dropdown format.
     *
     * @return a list of DropDownDto objects containing the item's ID and name
     */
    List<DropDownDto> getItemDropDownList();

    /**
     * Retrieves a item name by item id
     *
     * @param id to item id
     * @return item name
     */
    CommonResponse getItemNameById(Integer id);
}
