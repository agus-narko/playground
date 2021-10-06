package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	public class Item {
		private String itemName;
		private String itemContent;
		private int itemType;

		public Item() {
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getItemContent() {
			return itemContent;
		}

		public void setItemContent(String itemContent) {
			this.itemContent = itemContent;
		}

		public int getItemType() {
			return itemType;
		}

		public void setItemType(int itemType) {
			this.itemType = itemType;
		}

	}

	private List<Item> itemList = new ArrayList<>();

	@RequestMapping(value = "/admin/register", method = RequestMethod.GET)
	public void register(@RequestParam("itemName") String itemName, @RequestParam("itemContent") String itemContent,
			@RequestParam("itemType") int itemType) {

		Item newItem = new Item();
		newItem.setItemContent(itemContent);
		newItem.setItemName(itemName);
		newItem.setItemType(itemType);
		
		itemList.add(newItem);

	}

	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Item retrieve(@RequestParam("itemName") String itemName) {


		return itemList.stream().filter(item -> item.itemName.equals(itemName)).findFirst().get();
	}

	@RequestMapping(value = "/getType", method = RequestMethod.GET)
	public int getType(@RequestParam("itemName") String itemName) {

		return itemList.stream().filter(item -> item.itemName.equals(itemName)).findFirst().get().getItemType();

	}

	@RequestMapping(value = "/deregister", method = RequestMethod.GET)
	public void deregister(@RequestParam("itemName") String itemName) {

		itemList.removeIf(item -> item.itemName.equals(itemName));
	}

}