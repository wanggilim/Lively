package com.quadcore.lively.service;

import java.util.ArrayList;
import java.util.List;

import com.quadcore.lively.model.DashboardDAO;
import com.quadcore.lively.model.StmtVO;
import com.quadcore.lively.model.WordVO;

public class DashboardService {
	private DashboardDAO dao;
	
	public DashboardService() {
		dao = new DashboardDAO();
	}
	
	public List<WordVO> searchWords(String word) {
		return dao.searchWords(word);
	}

	public List<StmtVO> searchStatements(String word) {
		return dao.searchStatements(word);
	}

	/**
	 * 사용자의 입력 오타인지 확인해주기 위한 단어 리스트
	 * (= '이거 찾고 있나요?')
	 * 
	 * @author wgl
	 * @Date 2018.12.18
	 * @param word
	 * @param wordVOList
	 * @return perhapsKeywords
	 */
	public List<String> searchPerhapsWords(String word, List<WordVO> wordVOList) {
		List<String> perhapsKeywords = new ArrayList<>(1);
		
		for (WordVO wordVO : wordVOList) {
			
			// 사용자의 입력 오타인지 확인해주기 위한 단어 리스트 (= '이거 찾고 있나요?')
			if (! wordVO.getToken().equals(word)) {
				String keyword = wordVO.getToken();
				
				// 그 중에서 키워드가 중복되지 않는다면
				if (! perhapsKeywords.contains(keyword)) {
					perhapsKeywords.add(keyword);
				}
			}
			
		}
		
		return perhapsKeywords;
	}

	/**
	 * 토큰 테이블에서 단어의 뜻들을 추출함.
	 * 
	 * @author wgl
	 * @Date 2018.12.18
	 * @param wordVOList
	 * @return means
	 */
	public String getWordMeans(List<WordVO> wordVOList) {
		// 오타가 아니라면
		String means = "";
		
		for (WordVO wordVO : wordVOList) {
			String mean = wordVO.getMean();
			
			// 뜻이 중복되지 않는다면
			if (!means.startsWith(mean) && !means.endsWith(mean)) {
				means += (mean + " "); // 뜻 추가
			}
		}
		
		return means;
	}

}
