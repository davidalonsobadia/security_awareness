package org.security_awareness.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	public static <T> List<T> newArrayList(@SuppressWarnings("unchecked") T... t){
		List<T> arrayList = new ArrayList<>();
		arrayList.addAll(Arrays.asList(t));
		return arrayList;
	}
}
