package com.dytt;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import com.dytt.entity.*;
import com.dytt.utils.TimeHelper;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


public class MyDB extends DBHelper {
	private MyDB(){
	}
	public static List<Info> getInfos(int pagesize,int pageindex,String type){
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		Criteria criteria = session.createCriteria(Info.class);
		criteria.setFirstResult(pageindex*pagesize-pagesize);
		criteria.setMaxResults(pagesize);
		criteria.addOrder(Order.desc("pagenumber"));
		
		if(type!=null){
			criteria.add(Restrictions.eq("type", type));				
		}
		List<Info> infos = criteria.list();		
		trans.commit();
		session.close();
		
		return infos;
	}
	
	public static List<InfoSimple> getInfoSimples(int pagesize,int pageindex,String type,int year,int haspic){
		StringBuilder sql = new StringBuilder("select id,name,time from detail");
		if(type!=null){
			if("zongyi".equals(type)){
				sql.append(" where (type = \"zongyi\" or type = \"2009zongyi\" or type = \"zongyi2013\")");
			}else{
				sql.append(" where type = \"").append(type).append("\"");				
			}
		}
		if(year!=0){
			String start = year+"-00-00";
			String end = (year)+"-00-00";
			sql.append(" and time BETWEEN ").append(start).append(" and ").append(end);
		}
		sql.append(" order by pagenumber desc");
		sql.append(" limit ").append(pageindex*pagesize-pagesize).append(",").append(pagesize);
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		SQLQuery query = session.createSQLQuery(sql.toString()).addEntity(InfoSimple.class);
		List<InfoSimple> infos = query.list();
		setMoviePic(haspic, session, infos);
		trans.commit();
		session.close();
		return infos;
	}

	public static List<InfoSimple> getHotInfoSimples(int pagesize,int pageindex,int year,int haspic){
		//SELECT d.id,d.`name`,d.time from detail as d,moveget as m WHERE d.id=m.detailId ORDER BY m.count desc
		StringBuilder sql = new StringBuilder("SELECT d.id,d.`name`,d.time from detail as d,moveget as m WHERE d.id = m.detailId");
		if(year!=0){
			String start = year+"-00-00";
			String end = (year)+"-00-00";
			sql.append(" and time BETWEEN ").append(start).append(" and ").append(end);
		}
		sql.append(" ORDER BY m.count desc");
		sql.append(" limit ").append(pageindex*pagesize-pagesize).append(",").append(pagesize);
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sql.toString()).addEntity(InfoSimple.class);
		List<InfoSimple> infos = query.list();

		setMoviePic(haspic, session, infos);
		trans.commit();
		session.close();
		return infos;
	}

	public static int getHotCount(int year){
		StringBuilder sb = new StringBuilder("SELECT count(*) from detail as d,moveget as m WHERE d.id=m.detailId");
		if(year!=0){
			String start = year+"-00-00";
			String end = (year)+"-00-00";
			sb.append(" and time BETWEEN ").append(start).append(" and ").append(end);
		}

		Session session = getSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createSQLQuery(sb.toString());
		List<BigInteger> list = query.list();
		int count = list.get(0).intValue();
		trans.commit();
		session.close();
		return count;
	}

	private static void setMoviePic(int haspic, Session session, List<InfoSimple> infos) {
		if(haspic==1){
			for(InfoSimple info:infos){
				SQLQuery qurey = session.createSQLQuery("select imageUrl from imageurls as b where b.detailid = "+info.getId()+" limit 0,1");
				List<String> datas = qurey.list();
				if(datas.size()>0){
					info.setPic(datas.get(0));
				}
			}
		}
	}


	/**
	 * 
	 * @param pagesize
	 * @param pageindex
	 * @return
	 */
	public static List<InfoSimple> searchInfoSimples(int pagesize,int pageindex,String key){
		StringBuilder sql = new StringBuilder("select id,name,time from detail");
		
		sql.append(" where  NAME like '%"+key+"%' ");

		sql.append(" order by pagenumber desc");
		sql.append(" limit ").append(pageindex*pagesize-pagesize).append(",").append(pagesize);
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		SQLQuery query = session.createSQLQuery(sql.toString()).addEntity(InfoSimple.class);
		List<InfoSimple> infos = query.list();
		trans.commit();
		session.close();
		return infos;
	}
	
	public static boolean addFeedback(String text){
		Session session = getSession();
		Transaction trans = session.beginTransaction();	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		session.save(new Feedback(text,format.format(System.currentTimeMillis())));
		trans.commit();
		session.close();
		return true;
	}

	public static List<Feedback> getFeedback(int pagesize,int pageindex){
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		Criteria criteria = session.createCriteria(Feedback.class);
		criteria.setFirstResult(pageindex*pagesize-pagesize);
		criteria.setMaxResults(pagesize);
		criteria.addOrder(Order.desc("time"));
		List<Feedback> datas = criteria.list();		
		trans.commit();
		session.close();
		return datas;
	}
	
	public static int getFeedbackCount(){
		StringBuilder sb = new StringBuilder("select count(*) from feedback");
		Session session = getSession();
		Transaction trans = session.beginTransaction();
        Query query = session.createSQLQuery(sb.toString());
        List<BigInteger> list = query.list();
        int count = list.get(0).intValue();
        trans.commit();
        session.close();
		return count;
	}
	
	
	

	public static List<String> getGroups() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery sqlQuery = session.createSQLQuery("select type from detail group by type");
		List<String> list = sqlQuery.list();
		trans.commit();
		session.close();
		return list;
	}
	
	public static int getDetailCount(String type,int year){
		StringBuilder sb = new StringBuilder("select count(*) from detail");
		if(type!=null){
			if("zongyi".equals(type)){
				sb.append(" where (type = \"zongyi\" or type = \"2009zongyi\" or type = \"zongyi2013\")");
			}else{
				sb.append(" where type = '"+type+"'");				
			}
		}
		if(year!=0){
			String start = year+"-00-00";
			String end = (year)+"-00-00";
			sb.append(" and time BETWEEN ").append(start).append(" and ").append(end);
		}
		
		Session session = getSession();
		Transaction trans = session.beginTransaction();
        Query query = session.createSQLQuery(sb.toString());
        List<BigInteger> list = query.list();
        int count = list.get(0).intValue();
        trans.commit();
        session.close();
		return count;
	}
	
	public static int searchDetailCount(String key){
		StringBuilder sb = new StringBuilder("select count(*) from detail");
		
		sb.append(" where  NAME like '%"+key+"%' ");
		
		Session session = getSession();
		Transaction trans = session.beginTransaction();
        Query query = session.createSQLQuery(sb.toString());
        List<BigInteger> list = query.list();
        int count = list.get(0).intValue();
        trans.commit();
        session.close();
		return count;
	}

	public static Detail getDetail(int detailid) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		Criteria criteria = session.createCriteria(Detail.class);
		criteria.add(Restrictions.eq("id", detailid));
		List list = criteria.list();
		trans.commit();
		session.close();
		if(list.size()>0){
			return  (Detail)list.get(0);
		}
		return null;
	}

	public static List<String> getDownloadUrls(int detailid) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery qurey = session.createSQLQuery("select downloadurl from downloadurls as b where b.detailid = "+detailid);
		
		List<String> datas = qurey.list();
		trans.commit();
		session.close();
		return datas;
	}
	
	public static List<String> getImageurls(int detailid) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery qurey = session.createSQLQuery("select imageUrl from imageurls as b where b.detailid = "+detailid);
		
		List<String> datas = qurey.list();
		trans.commit();
		session.close();
		return datas;
	}
	public static String getFirstImageurl(int detailid) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery qurey = session.createSQLQuery("select imageUrl from imageurls as b where b.detailid = "+detailid+" limit 0,1");
		List<String> datas = qurey.list();
		trans.commit();
		session.close();
		if(datas.size()>0){
			return datas.get(0);
		}
		return "";
	}

	public static String getDetailContent(int detailid) {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery qurey = session.createSQLQuery("select content from detail as b where b.id = "+detailid);
		List<String> datas = qurey.list();
		trans.commit();
		session.close();
		if(datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	
	public static Config getConfig(){
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		Criteria criteria = session.createCriteria(Config.class);
		criteria.addOrder(Order.desc("id"));
		List<Config> datas = criteria.list();
		trans.commit();
		session.close();
		if(datas.size()>0){
			return datas.get(0);
		}
		return null;
	}
	
	public static boolean insertLoginHistory(LoginHistory history){
		Session session = getSession();
		Transaction trans = session.beginTransaction();		
		session.save(history);
		trans.commit();
		session.close();
		return true;
	}

	public static boolean updateGetMove(String detailid){
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select COUNT(*) from moveget as b where b.detailId = "+detailid);
		List<BigInteger> list = query.list();
		if(list.get(0).intValue()>0){
           session.createSQLQuery("update moveget as b set b.count = b.count +1 WHERE b.detailId = "+detailid).executeUpdate();
		}else {
			session.save(new MovieGetInfo(0,detailid,1));
		}
		trans.commit();
		session.close();
		return true;
	}


	/**
	 * @return
	 */
	public static List<InfoSimple> updateDetailsTimes(){
		StringBuilder sql = new StringBuilder("select id,time,name from detail");
		sql.append(" order by pagenumber desc");
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sql.toString()).addEntity(InfoSimple.class);


		List<InfoSimple> infos = query.list();
		for (InfoSimple info:infos){
			if(info.getTime()!=null && info.getTime().length() ==8){
				String time = TimeHelper.format(info.getTime());
				session.createSQLQuery("update detail as b set b.time = '"+time+"' WHERE b.id = "+info.getId()).executeUpdate();
				System.out.println(time);
			}

			System.out.println(info.toString());
		}
		System.out.println(infos.size());
		trans.commit();
		session.close();
		return infos;
	}


	public static boolean addCardData(int cardId, String cardData){
		boolean flag = false;

		Session session = getSession();
		Transaction trans = session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select card_id from appsearch_all_cards where card_id = "+cardId);
		int size = query.list().size();
		if(size == 0){
			session.save(new CardData(cardId, cardData));
			flag = true;
		}
		trans.commit();
		session.close();
		return flag;
	}



	
	public static void main(String[] args) {
		updateDetailsTimes();
//		LoginHistory history = new LoginHistory();
//		history.setDeviceId("fdafdafdaf");
//		history.setLoginTime("2014-11-23");
//		insertLoginHistory(history);

//		String url = getFirstImageurl(22);
//		System.out.println(url);

//		updateGetMove(2222+"");
	}
	
}
