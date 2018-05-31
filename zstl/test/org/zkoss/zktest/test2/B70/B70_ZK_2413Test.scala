package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.openqa.selenium.Keys
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2413.zul")
class B70_ZK_2413Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2413.zul

	Purpose:
		
	Description:
		
	History:
		Tue, Aug 26, 2014  15:15:00 PM, Created by jerrychen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<zk>
    <zscript><![CDATA[
		
		public class BigList extends AbstractList {
		 
		    private int size;
		 
		    public BigList(int sz) {
		        if (sz < 0)
		            throw new IllegalArgumentException("Negative not allowed: " + sz);
		        size = sz;
		    }
		 
		    public int size() {
		        return size;
		    }
		 
		    public Object get(int j) {
		        return Integer.valueOf(j);
		    }
		 
		}
		
        List items = new BigList(1000); //a big list of Integer
    ]]></zscript>
    <div>
    	1. using keyboard, when input is focusing, press "PageUp" to let page go back. e.g. p.2 => p.1
    	<separator/>
    	2. using keyboard, when input is focusing, press "PageDown" to let page go next. e.g. p.1 => p.2
    	<separator/>
    	3. when you press "PageUp", "PageDown" and "Enter", all they will trigger page change.
    	<separator/>
    	4. if one of them doesn't work well, it's a bug.
    </div>
    <listbox id="listbox" mold="paging" pageSize="10" pagingPosition="both">
        <listitem forEach="${items}">
            <listcell label="${each}-1" />
            <listcell label="${each}-2" />
            <listcell label="${each}-3" />
            <listcell label="${each}-4" />
        </listitem>
    </listbox>
    
    <grid width="300px" mold="paging" pageSize="4">
	    <columns>
	        <column label="Left"/>
	        <column label="Right"/>
	    </columns>
	    <rows>
	        <row>
	            <label value="Item 1.1"/><label value="Item 1.2"/>
	        </row>
	        <row>
	            <label value="Item 2.1"/><label value="Item 2.2"/>
	        </row>
	        <row>
	            <label value="Item 3.1"/><label value="Item 3.2"/>
	        </row>
	        <row>
	            <label value="Item 4.1"/><label value="Item 4.2"/>
	        </row>
	        <row>
	            <label value="Item 5.1"/><label value="Item 5.2"/>
	        </row>
	        <row>
	            <label value="Item 6.1"/><label value="Item 6.2"/>
	        </row>
	        <row>
	            <label value="Item 7.1"/><label value="Item 7.2"/>
	        </row>
	    </rows>
	</grid>
	<tree id="tree" rows="5" mold="paging" pageSize="4">
        <treecols sizable="true">
            <treecol label="Name" />
            <treecol label="Description" />
        </treecols>
        <treechildren>
            <treeitem>
                <treerow>
                    <treecell label="Item 1" />
                    <treecell label="Item 1 description" />
                </treerow>
            </treeitem>
            <treeitem>
                <treerow>
                    <treecell label="Item 2" />
                    <treecell label="Item 2 description" />
                </treerow>
                <treechildren>
                    <treeitem>
                        <treerow>
                            <treecell label="Item 2.1" />
                        </treerow>
                    </treeitem>
                    <treeitem>
                        <treerow>
                            <treecell label="Item 2.2" />
                            <treecell
                                label="Item 2.2 is something who cares" />
                        </treerow>
                    </treeitem>
                </treechildren>
            </treeitem>
            <treeitem label="Item 3" />
        </treechildren>
        <treefoot>
            <treefooter label="Count" />
            <treefooter label="Summary" />
        </treefoot>
    </tree>

</zk>

"""
    runZTL(zscript,
      () => {
        val inps = jq(".z-paging-input");

        // first input
        var inp = inps.eq(0);
        focus(inp);
        waitResponse();
        var target = jq("@listcell").eq(0).find("div");
        var value = target.html();

        sendKeys(inp, Keys.ARROW_UP);
        waitResponse();
        sendKeys(inp, Keys.ENTER);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_UP);
        waitResponse();
        verifyTrue(inp.attr("value").equals("1"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_DOWN);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        // second input
        inp = inps.eq(1);
        focus(inp);
        waitResponse();
        target = jq("@listcell").eq(0).find("div");
        value = target.html();

        sendKeys(inp, Keys.ARROW_DOWN);
        waitResponse();
        sendKeys(inp, Keys.ENTER);
        waitResponse();
        verifyTrue(inp.attr("value").equals("1"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_DOWN);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_UP);
        waitResponse();
        verifyTrue(inp.attr("value").equals("1"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        // third input
        inp = inps.eq(2);
        focus(inp);
        waitResponse();
        target = jq("@row .z-label").first();
        value = target.html();

        sendKeys(inp, Keys.ARROW_UP);
        waitResponse();
        sendKeys(inp, Keys.ENTER);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_UP);
        waitResponse();
        verifyTrue(inp.attr("value").equals("1"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_DOWN);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        // fourth input
        inp = inps.eq(3);
        focus(inp);
        waitResponse();
        target = jq(".z-treecell-text").first()
        value = target.html();

        sendKeys(inp, Keys.ARROW_UP);
        waitResponse();
        sendKeys(inp, Keys.ENTER);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_UP);
        waitResponse();
        verifyTrue(inp.attr("value").equals("1"));
        verifyFalse(value.equals(target.html()));
        value = target.html();

        sendKeys(inp, Keys.PAGE_DOWN);
        waitResponse();
        verifyTrue(inp.attr("value").equals("2"));
        verifyFalse(value.equals(target.html()));
        value = target.html();
      })

  }
}