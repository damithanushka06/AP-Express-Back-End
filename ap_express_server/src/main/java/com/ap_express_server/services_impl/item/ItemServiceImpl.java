package com.ap_express_server.services_impl.item;

import com.ap_express_server.models.common.CommonResponse;
import com.ap_express_server.models.dropdown.DropDownDto;
import com.ap_express_server.models.item.Item;
import com.ap_express_server.models.item.ItemDto;
import com.ap_express_server.repository.category.CategoryRepository;
import com.ap_express_server.repository.item.ItemRepository;
import com.ap_express_server.repository.uom.UOMRepository;
import com.ap_express_server.service.item.ItemService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    private final UOMRepository uomRepository;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, UOMRepository uomRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
    }

    /**
     * this method used for save item details in item table
     *
     * @param item to Item reference
     * @return to save function of repo
     */
    @Override
    public Item createItem(Item item) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        item.setCreatedBy(currentUserName);
        item.setCreatedDate(LocalDate.now());
        return itemRepository.save(item);
    }

    /**
     * this method used for get category list
     */
    @Override
    public List<DropDownDto> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    /**
     * this method used for get uom list
     */
    @Override
    public List<DropDownDto> getUOMList() {
        return uomRepository.getUOMList();
    }

    /**
     * this method used for get all items
     */
    @Override
    public List<ItemDto> getAllItemList() {
        return itemRepository.getAllItemList();
    }

    /**
     * this method used for get item detail by id
     *
     * @param itemId to item master id
     */
    @Override
    public Optional<Item> getItemDetailById(Integer itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Item getItem = item.get();
            return Optional.of(getItem);
        } else {
            return Optional.empty();
        }
    }

    /**
     * this method used for delete item detail by id
     *
     * @param itemId to item master id
     */
    @Override
    public void deleteItemDetailById(Integer itemId) {
        itemRepository.deleteById(itemId);
    }

    /**
     * this method used for update item
     */
    @Override
    public Item updateItem(Item item) {
        Optional<Item> existItem = itemRepository.findById(item.getId());
        if (existItem.isPresent()) {
            Item existItemObject = existItem.get();
            existItemObject.setName(item.getName());
            existItemObject.setDescription(item.getDescription());
            existItemObject.setUomId(item.getUomId());
            existItemObject.setUnitPrice(item.getUnitPrice());
            existItemObject.setCategoryId(item.getCategoryId());
            return itemRepository.save(existItemObject);
        } else {
            throw new NoSuchElementException("Item not found with id: " + item.getId());
        }
    }

    /**
     * retrieve item dropdown
     *
     * @return item dropdown list
     */
    @Override
    public List<DropDownDto> getItemDropDownList() {
        return itemRepository.getItemDropDownList();
    }

    /**
     * retrieve item name by item id
     *
     * @return string name
     */
    @Override
    public CommonResponse getItemNameById(Integer id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            Item presentItem = item.get();
            return new CommonResponse(presentItem.getName());
        } else {
            throw new NoSuchElementException("Item not found with id: " + id);
        }
    }


}
