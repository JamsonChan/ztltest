package org.zkoss.zktest.test2.B65

import org.zkoss.ztl.Tags
import org.zkoss.zstl.ZTL4ScalaTestCase

@Tags(tags = "B60-ZK-910.zul")
class B60_ZK_910Test extends ZTL4ScalaTestCase {

  def testClick() = {
    val zscript = """<zk>
                    Please click the column's menu to group the data, if it cannot work that's a bug.
                    <zscript><![CDATA[
		import java.util.Calendar;
		import java.text.DateFormat;
		import java.text.SimpleDateFormat;
		import org.zkoss.lang.reflect.Fields;
		
		public class SampleBean {
			private String name;
			private int number;
			private Date date;

			public SampleBean(String name, int number, Date date) {
				super();
				this.name = name;
				this.number = number;
				this.date = date;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getNumber() {
				return number;
			}

			public void setNumber(int number) {
				this.number = number;
			}

			public Date getDate() {
				return date;
			}

			public void setDate(Date date) {
				this.date = date;
			}
		}
		
		
		int count = 10;
		
		List list = new ArrayList(count);
		Random rand = new Random(new Random().nextLong());
		
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < count; i++) {
			list.add(new SampleBean("item " + rand.nextInt(count),
					rand.nextInt(count), cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		GroupsModelArray model = 
			new org.zkoss.zktest.test2.B60_ZK_910(list, new FieldComparator("name", true));
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		RowRenderer renderer = new RowRenderer() {
			public void render(Row row, Object data, int index) {
				if (row instanceof Group) {
					Object[] obj = (Object[]) data; // prepared by
					// createGroupHead()
					row.appendChild(new Label(
							getGroupHead(row, (SampleBean) obj[0], ((Integer) obj[1]).intValue())));
				} else {
					SampleBean b = (SampleBean) data;
					row.appendChild(new Label(b.getName()));
					row.appendChild(new Label(b.getNumber()+""));
					row.appendChild(new Label(df.format(b.getDate())));
				}
			}
			
			private String getGroupHead(Row row, SampleBean bean, int index) {
				Column column = 
					(Column) row.getGrid().getColumns().getChildren().get(index);
				String field =  //print: category
					((FieldComparator) column.getSortAscending()).getRawOrderBy();
				try {
					return Fields.get(bean, field).toString();
				} catch (NoSuchMethodException ex) {
					throw UiException.Aide.wrap(ex);
				}
			}
		};
    ]]></zscript>
                    <grid model="${model}" rowRenderer="${renderer}">
                      <columns menupopup="auto">
                        <column label="name" sort="auto(name)"/>
                        <column label="number" sort="auto(number)"/>
                        <column label="date" sort="auto(date)"/>
                      </columns>
                    </grid>
                  </zk>"""

    runZTL(zscript,
      () => {
        sleep(3000)
        verifyEquals(jq("."), "")
      })

  }
}
