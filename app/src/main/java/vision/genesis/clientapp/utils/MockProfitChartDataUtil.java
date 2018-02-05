package vision.genesis.clientapp.utils;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class MockProfitChartDataUtil
{
	private static final int PRIMARY_ENTRIES_COUNT = 10;

	private static final int EXPANDED_ENTRIES_COUNT = 20;

	public static List<Entry> getEntries() {
		List<Entry> entries = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < PRIMARY_ENTRIES_COUNT; i++) {
			entries.add(new Entry(i, random.nextFloat() * 400 - 175));
		}

		List<Entry> expandedEntries = new ArrayList<>();
		for (int i = 0; i < entries.size() - 1; i++) {
			expandedEntries.add(entries.get(i));
			Entry left = entries.get(i);
			Entry right = entries.get(i + 1);
			int expandedEntriesCount = EXPANDED_ENTRIES_COUNT;
			float stepX = (right.getX() - left.getX()) / expandedEntriesCount;
			float stepY = (right.getY() - left.getY()) / expandedEntriesCount;
			for (int j = 0; j < expandedEntriesCount; j++) {
				expandedEntries.add(new Entry(left.getX() + j * stepX, left.getY() + j * stepY));
			}
		}
		expandedEntries.add(entries.get(entries.size() - 1));

		return expandedEntries;
	}
}