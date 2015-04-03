package com.andy.indexexpandablelistview;

import com.andy.util.StringMatcher;
import com.andy.widget.IndexExpandableListView;
import com.any.indexexpandablelistview.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private IndexExpandableListView mListView;
    // groups
    private String[] expressTypes = new String[] {"A-3", "B-4", "C-5", "D-7", "E-1", "F-6",
    		"G-2", "H-4", "J-8", "K-3", "L-5", "M-3", "N-1",
    		"O-1", "P-1", "Q-4", "R-2", "S-9", "T-6", "W-4",
    		"X-4", "Y-15", "Z-6"};
    
    // group items
    private String[][] expressNames = new String[][] {
            {"安信达", "AAE中国", "安捷快递"},
            {"百世汇通", "百福东方", "BHT中国", "百世快运"},
            {"创一快递", "城市之星", "传志快递", "长通物流", "城联速递"},
            {"德邦物流", "DHL中国", "D速快递", "东方快递", "大田物流", "递四方", "DPEX中国"},
            {"EMS"},
            {"飞康达", "飞邦物流", "飞豹快递", "丰达快递", "凤凰快递", "FedEx"},
            {"国通快递", "广东邮政"},
            {"海红网送", "恒路物流", "海盟物流", "海外环球"},
            {"捷特快递", "佳吉快运", "加运美", "佳怡物流", "晋越快递", "嘉里大通", "金大物流", "京广速递"},
            {"康力物流", "快捷速递", "跨越速运"},
            {"联昊通", "龙邦速递", "乐捷递", "立即送", "蓝镖快递"},
            {"明亮物流", "闽盛快递", "门对门"},
            {"能达快递"},
            {"OCS中外运"},
            {"平安达"},
            {"全峰快递", "全晨快递", "全一快递", "全日通"},
            {"如风达", "日昱物流"},
            {"赛澳递", "顺丰速递", "申通快递", "速尔快递", "盛辉物流", "上大物流", "圣安物流", "三态物流", "盛丰物流"},
            {"天天快递", "天地华宇", "TNT中国", "通成物流", "通和天下", "腾讯达"},
            {"万家物流", "微特派", "伟邦速递", "伍圆速递"},
            {"西安聚信", "新邦物流", "信丰物流", "鑫飞鸿"},
            {"圆通速递", "韵达快递", "优速快递", "源安达", "远成物流", "运通快递", "银捷速递", "亚风速递", "原飞航", "一邦速递", "源伟丰", "元智捷诚", "越丰物流", "一统飞鸿", "云物通"},
            {"中通快递", "宅急送", "中邮物流", "芝麻开门", "中铁快运", "中信达"}

    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
						
		mListView = (IndexExpandableListView) findViewById(R.id.indexExpandListview);
		// 初始化数据,创建adapter
		IndexExpandableListViewAdapter adapter = new IndexExpandableListViewAdapter();
		mListView.setAdapter(adapter);
		mListView.setFastScrollEnabled(true);
		
		// 展开数据
		for (int i = 0; i < 23; i ++) {
			mListView.expandGroup(i);
		}		
		// 列表items点击
		mListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, expressNames[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
			
	class IndexExpandableListViewAdapter extends BaseExpandableListAdapter implements SectionIndexer {
		
		String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		/*// 构造函数中可传递数据等，这里仅演示效果
		public IndexExpandListViewAdapter (Context context) {
			
		}*/

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return expressTypes.length;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return expressNames[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return expressTypes[groupPosition];
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return expressNames[groupPosition][childPosition];
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 最好写在xml布局文件中，这里只是演示效果，由代码生成View
			LinearLayout layout = new LinearLayout(MainActivity.this);
			layout.setOrientation(LinearLayout.HORIZONTAL);         
            TextView textView = getTextView();
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(22);
            textView.setPadding(100, 0, 0, 0);
            textView.setText(getGroup(groupPosition).toString());
            layout.addView(textView);

            return layout;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 最好写在xml布局文件中，这里只是演示效果，由代码生成View
			LinearLayout layout = new LinearLayout(MainActivity.this);
			layout.setOrientation(LinearLayout.HORIZONTAL);          
            TextView textView = getTextView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            layout.addView(textView);
            
            return layout;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public Object[] getSections() {
			// TODO Auto-generated method stub
			String[] sections = new String[mSections.length()];
			for (int i = 0; i < mSections.length(); i++) {
				sections[i] = String.valueOf(mSections.charAt(i));
			}
			return sections;
		}

		@Override
		public int getPositionForSection(int sectionIndex) {
			// TODO Auto-generated method stub		
			for (int i = sectionIndex; i >= 0; i--) {
				for (int j = 0; j < getGroupCount(); j++) {
					if (i == 0) {
						// #
						// For numeric section
						for (int k = 0; k <= 9; k++) {
							if (StringMatcher.match(String.valueOf(getGroup(j).toString().charAt(0)), String.valueOf(k))) {
								return j;
							}				
						}
					} else {
						if (StringMatcher.match(String.valueOf(getGroup(j).toString().charAt(0)), String.valueOf(mSections.charAt(i)))) {						
							return j;
						}			
					}
				}
			}
									
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		// 自定义一个TextView,仅演示效果
		private TextView getTextView() {
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			TextView textView = new TextView(MainActivity.this);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER_VERTICAL);
			textView.setPadding(40, 0, 0, 0);
			textView.setTextSize(20);
			textView.setTextColor(Color.BLACK);
			return textView;
		}
		
	}

}
