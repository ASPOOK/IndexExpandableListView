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
            {"���Ŵ�", "AAE�й�", "���ݿ��"},
            {"������ͨ", "�ٸ�����", "BHT�й�", "��������"},
            {"��һ���", "����֮��", "��־���", "��ͨ����", "�����ٵ�"},
            {"�°�����", "DHL�й�", "D�ٿ��", "�������", "��������", "���ķ�", "DPEX�й�"},
            {"EMS"},
            {"�ɿ���", "�ɰ�����", "�ɱ����", "�����", "��˿��", "FedEx"},
            {"��ͨ���", "�㶫����"},
            {"��������", "��·����", "��������", "���⻷��"},
            {"���ؿ��", "�Ѽ�����", "������", "��������", "��Խ���", "�����ͨ", "�������", "�����ٵ�"},
            {"��������", "����ٵ�", "��Խ����"},
            {"���ͨ", "�����ٵ�", "�ֽݵ�", "������", "���ڿ��"},
            {"��������", "��ʢ���", "�Ŷ���"},
            {"�ܴ���"},
            {"OCS������"},
            {"ƽ����"},
            {"ȫ����", "ȫ�����", "ȫһ���", "ȫ��ͨ"},
            {"����", "��������"},
            {"���ĵ�", "˳���ٵ�", "��ͨ���", "�ٶ����", "ʢ������", "�ϴ�����", "ʥ������", "��̬����", "ʢ������"},
            {"������", "��ػ���", "TNT�й�", "ͨ������", "ͨ������", "��Ѷ��"},
            {"�������", "΢����", "ΰ���ٵ�", "��Բ�ٵ�"},
            {"��������", "�°�����", "�ŷ�����", "�ηɺ�"},
            {"Բͨ�ٵ�", "�ϴ���", "���ٿ��", "Դ����", "Զ������", "��ͨ���", "�����ٵ�", "�Ƿ��ٵ�", "ԭ�ɺ�", "һ���ٵ�", "Դΰ��", "Ԫ�ǽݳ�", "Խ������", "һͳ�ɺ�", "����ͨ"},
            {"��ͨ���", "լ����", "��������", "֥�鿪��", "��������", "���Ŵ�"}

    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
						
		mListView = (IndexExpandableListView) findViewById(R.id.indexExpandListview);
		// ��ʼ������,����adapter
		IndexExpandableListViewAdapter adapter = new IndexExpandableListViewAdapter();
		mListView.setAdapter(adapter);
		mListView.setFastScrollEnabled(true);
		
		// չ������
		for (int i = 0; i < 23; i ++) {
			mListView.expandGroup(i);
		}		
		// �б�items���
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
		
		/*// ���캯���пɴ������ݵȣ��������ʾЧ��
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
			// ���д��xml�����ļ��У�����ֻ����ʾЧ�����ɴ�������View
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
			// ���д��xml�����ļ��У�����ֻ����ʾЧ�����ɴ�������View
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
		
		// �Զ���һ��TextView,����ʾЧ��
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
