package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(saveItem.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    public void findAll() throws Exception{
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 10000, 10);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    public void updateItem() throws Exception{
        //given
        Item item1 = new Item("itemA", 10000, 10);
        itemRepository.save(item1);
        //when
        Item item2 = new Item("itemB", 10000, 10);
        itemRepository.update(item1.getId(), item2);
        Item findItem = itemRepository.findById(item1.getId());
        //then
        assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());

    }
}