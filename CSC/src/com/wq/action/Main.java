package com.wq.action;

import java.util.List;

import com.wq.dao.ScoreDAO;
import com.wq.daoImp.ScoreDAOImp;
import com.wq.entity.Score;

public class Main {
	public static void main(String[] args) throws Exception {
		ScoreDAO dao = new ScoreDAOImp();
		List<Score> list = dao.selectScoreBy("Ro", "数学");
		System.out.println(list.size());
	}
}
