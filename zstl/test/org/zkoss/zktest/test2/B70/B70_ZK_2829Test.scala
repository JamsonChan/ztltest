package org.zkoss.zktest.test2.B70

import org.junit.Test
import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags

@Tags(tags = "B70-ZK-2829.zul")
class B70_ZK_2829Test extends ZTL4ScalaTestCase {

  @Test
  def testClick() = {
    val zscript =
      """

<?xml version="1.0" encoding="UTF-8"?>

<!--
B70-ZK-2829.zul

	Purpose:
		
	Description:
		
	History:
		Tue Jul 28 10:10:39 CST 2015, Created by jumperchen

Copyright (C)  Potix Corporation. All Rights Reserved.

-->
<window>
    <zscript><![CDATA[
ListModelList model = new ListModelList();
model.add("A");
model.add("B");
model.add("C");
model.addToSelection("A");
]]></zscript>
    <label multiline="true">
        1. Please click the button "Serialize"
        2. And then click the button "add new item"
        3. You should not see any Exception dialog appearing.
    </label>
    <tabbox id="tabbox" model="${model}">
        <template name="model:tab">
            <tab label="${each}"></tab>
        </template>
        <template name="model:tabpanel">
            <tabpanel>Panel ${each}
                <label value="click me ${each}"></label>
            </tabpanel>
        </template>
    </tabbox>
    <button label="Serialize">
        <attribute name="onClick">
            import org.zkoss.zk.ui.sys.*;
            import java.io.ByteArrayInputStream;
            import java.io.ByteArrayOutputStream;
            import java.io.ObjectInputStream;
            import java.io.ObjectOutputStream;
            import java.io.Serializable;
            import java.util.ArrayList;
            import java.util.List;

            Page pg = tabbox.getPage();
            ((ComponentCtrl)tabbox).sessionWillPassivate(pg);//simulate

            ByteArrayOutputStream oaos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(oaos);
            oos.writeObject(tabbox);
            oos.close();
            oaos.close();

            ByteArrayInputStream oais = new ByteArrayInputStream(oaos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(oais);

            Tabbox newtabbox = (Tabbox) ois.readObject();
            Component parent = tabbox.getParent();
            tabbox.detach();
            ois.close();
            oais.close();
            parent.insertBefore(newtabbox, self);
            model = newtabbox.getModel();
            ((ComponentCtrl)newtabbox).sessionDidActivate(newtabbox.getPage());//simulate
        </attribute>
    </button>
    <button label="add new item">
        <attribute name="onClick">
            model.add("D");
        </attribute>
    </button>
</window>

"""
    runZTL(zscript,
      () => {
        click(jq("button").eq(0))
        waitResponse()
        click(jq("button").eq(1))
        waitResponse()
        verifyFalse(hasError())
      })
  }
}