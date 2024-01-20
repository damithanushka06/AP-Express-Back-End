package com.ap_express_server.controllers.item;
import com.ap_express_server.constant.Notification.HttpUrlConstants;
import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.item.Item;
import com.ap_express_server.models.item.ItemDto;
import com.ap_express_server.service.item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = HttpUrlConstants.FRONTEND_BASE_URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth/")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * this method can be used to create Item
     * @param item to item model
     * @return to the service
     */
    @PostMapping("item/create_item")
    public Item createItem(@RequestBody Item item){
        return itemService.createItem(item);
    }


    /**
     * this method can be used for load category list
     */
    @GetMapping("item/get_category_list")
    public List<DropDownDto> getCategory(){
        return  itemService.getCategoryList();
    }

    /**
     * this method can be used for load uom list
     */
    @GetMapping("item/get_uom_list")
    public List<DropDownDto> getUOMList(){
        return  itemService.getUOMList();
    }

    /**
     * this method can be used for load all item list
     */
    @GetMapping("item/getAll")
    public List<ItemDto> getItemList(){return itemService.getAllItemList();}

    /**
     * this method can be used to get item details by item id
     * @param itemId id to item master id
     */
    @GetMapping("item/get_item_detail_by_id")
    public Optional<Item> getItemDetailById(@RequestParam Integer itemId) {
        return itemService.getItemDetailById(itemId);
    }


    /**
     * this method can be used to delete selected item
     * @param itemId to item id
     */
    @DeleteMapping("item/delete_item_detail_by_id")
    public ResponseEntity<?> deleteItemById(@RequestParam Integer itemId) {
        itemService.deleteItemDetailById(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("item/update_item")
    private Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }


    /**
     * this method can be used for get item list
     */
    @GetMapping("item/get_name_by_id")
    public CommonResponse getItemNameById(@RequestParam Integer itemId){
        return  itemService.getItemNameById(itemId);
    }

}
