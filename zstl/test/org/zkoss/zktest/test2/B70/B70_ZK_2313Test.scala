package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2313.zul")
class B70_ZK_2313Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2313.zul

	Purpose:
		
	Description:
		
	History:
		Fri, May 30, 2014 11:45:11 AM, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
<zscript><![CDATA[
          		ListModel model1 = new org.zkoss.zktest.test2.grid.FakeListModel(200);
        		ListModel model2 = new org.zkoss.zktest.test2.grid.FakeListModel(200);
        		ListModel model3 = new org.zkoss.zktest.test2.grid.FakeListModel(200);
        		ListModel model4 = new org.zkoss.zktest.test2.grid.FakeListModel(200);
		String[] array = new String[50];
		for (int i =0; i < array.length; i++)
			array[i] = "data " + i;
		
		]]></zscript>
	<vlayout>
	Native scrollbar
		<hlayout>
		<listbox id="list1" width="200px" rows="10" model="&#36;{model1}">
			<custom-attributes org.zkoss.zul.listbox.rod="true" org.zkoss.zul.nativebar="true"/>
			<listhead>
				<listheader label="Model ROD True" sort="auto"/>
			</listhead>
		</listbox>
		<listbox id="list2" width="200px" rows="10" model="&#36;{model2}">
			<custom-attributes org.zkoss.zul.listbox.rod="false" org.zkoss.zul.nativebar="true"/>
			<listhead>
				<listheader label="Model ROD False" sort="auto"/>
			</listhead>
		</listbox>
		<listbox id="list3" width="200px" rows="10">
			<listhead>
				<listheader label="Foreach with ROD True" sort="auto"/>
			</listhead>
			<custom-attributes org.zkoss.zul.listbox.rod="true" org.zkoss.zul.nativebar="true"/>
			<listitem label="${each}" forEach="${array}" />
		</listbox>
		<listbox id="list4" width="200px" rows="10">
			<listhead>
				<listheader label="Foreach with ROD False" sort="auto"/>
			</listhead>
			<custom-attributes org.zkoss.zul.listbox.rod="false" org.zkoss.zul.nativebar="true"/>
			<listitem label="${each}" forEach="${array}" />
		</listbox>
		</hlayout>
	Customized scrollbar
		<hlayout>
		<listbox id="list5" width="200px" rows="10" model="&#36;{model3}">
			<custom-attributes org.zkoss.zul.listbox.rod="true" org.zkoss.zul.nativebar="false"/>
			<listhead>
				<listheader label="Model ROD True" sort="auto"/>
			</listhead>
		</listbox>
		<listbox id="list6" width="200px" rows="10" model="&#36;{model4}">
			<custom-attributes org.zkoss.zul.listbox.rod="false" org.zkoss.zul.nativebar="false"/>
			<listhead>
				<listheader label="Model ROD False" sort="auto"/>
			</listhead>
		</listbox>
		<listbox id="list7" width="200px" rows="10">
			<custom-attributes org.zkoss.zul.listbox.rod="true" org.zkoss.zul.nativebar="false"/>
			<listhead>
				<listheader label="Foreach with ROD True" sort="auto"/>
			</listhead>
			<listitem label="${each}" forEach="${array}" />
		</listbox>
		<listbox id="list8" width="200px" rows="10">
			<custom-attributes org.zkoss.zul.listbox.rod="false" org.zkoss.zul.nativebar="false"/>
			<listhead>
				<listheader label="Foreach with ROD False" sort="auto"/>
			</listhead>
			<listitem label="${each}" forEach="${array}" />
		</listbox>
		</hlayout>
	</vlayout>
	1. Please click the first button "Test Listitem.setSelected", and then all the listboxes should scroll to the last one.
	<separator/>
	2. Please click the second button "Test Listbox.setSelectedIndex()", and then all the listboxes should scroll to the first one.
	<separator/>
	3. Please click the third button "Test Listbox.setSelectedItem()", and then all the listboxes should scroll to the last one.
	<separator/>
	<button id="b1" label="Test Listitem.setSelected()">
		<attribute name="onClick">
		model1.addToSelection(model1.getElementAt(model1.getSize()-1));
		model2.addToSelection(model2.getElementAt(model1.getSize()-1));
		model3.addToSelection(model3.getElementAt(model1.getSize()-1));
		model4.addToSelection(model4.getElementAt(model1.getSize()-1));
		
		list3.getLastChild().setSelected(true);
		list4.getLastChild().setSelected(true);
		list7.getLastChild().setSelected(true);
		list8.getLastChild().setSelected(true);
		</attribute>
	</button>
	<button id="b2" label="Test Listbox.setSelectedIndex()">
		<attribute name="onClick">
		model1.addToSelection(model1.getElementAt(0));
		model2.addToSelection(model2.getElementAt(0));
		model3.addToSelection(model3.getElementAt(0));
		model4.addToSelection(model4.getElementAt(0));
		
		list3.setSelectedIndex(0);
		list4.setSelectedIndex(0);
		list7.setSelectedIndex(0);
		list8.setSelectedIndex(0);
		</attribute>
	</button>
	<button id="b3" label="Test Listbox.setSelectedItem()">
		<attribute name="onClick">
		model1.addToSelection(model1.getElementAt(model1.getSize()-1));
		model2.addToSelection(model2.getElementAt(model1.getSize()-1));
		model3.addToSelection(model3.getElementAt(model1.getSize()-1));
		model4.addToSelection(model4.getElementAt(model1.getSize()-1));
		
		list3.setSelectedItem(list3.getLastChild());
		list4.setSelectedItem(list4.getLastChild());
		list7.setSelectedItem(list7.getLastChild());
		list8.setSelectedItem(list8.getLastChild());
		</attribute>
	</button>
</zk>
"""
    runZTL(zscript,
      () => {

        val b1 = jq("$b1");
        val b2 = jq("$b2");
        val b3 = jq("$b3");
        val lists = jq("@listbox");
        var scrollFail = false;

        click(b1);
        waitResponse();
        var iter = lists.iterator();
        while (iter.hasNext()) {
          val list = iter.next();
          println(getScrollTop(list.toWidget()));
          if (getScrollTop(list.toWidget()) == 0)
            scrollFail = true;
        }

        click(b2);
        waitResponse();
        iter = lists.iterator();
        while (iter.hasNext()) {
          val list = iter.next();
          println(getScrollTop(list.toWidget()));
          if (getScrollTop(list.toWidget()) > 0)
            scrollFail = true;
        }

        click(b3);
        waitResponse();
        iter = lists.iterator();
        while (iter.hasNext()) {
          val list = iter.next();
          println(getScrollTop(list.toWidget()));
          if (getScrollTop(list.toWidget()) == 0)
            scrollFail = true;
        }

        verifyFalse("scrolling should be changed after button click.", scrollFail);
      })

  }
}