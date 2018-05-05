package com.tts168.autoset.tools.localmusic;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class MusicPinyinComparator implements Comparator<MusicSortModel> {

	public int compare(MusicSortModel o1, MusicSortModel o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
