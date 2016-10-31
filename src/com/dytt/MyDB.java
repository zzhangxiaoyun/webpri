package com.dytt;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.dytt.entity.Config;
import com.dytt.entity.Detail;
import com.dytt.entity.Feedback;
import com.dytt.entity.Info;
import com.dytt.entity.InfoSimple;
import com.dytt.entity.LoginHistory;


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
	
	public static List<InfoSimple> getInfoSimples(int pagesize,int pageindex,String type,int year){
		StringBuilder sql = new StringBuilder("select id,name,time from detail");
		if(type!=null){
			if("zongyi".equals(type)){
				sql.append(" where (type = \"zongyi\" or type = \"2009zongyi\" or type = \"zongyi2013\")");
			}else{
				sql.append(" where type = \"").append(type).append("\"");				
			}
		}
		if(year!=0){
			int start = year*10000;
			int end = (year+1)*10000;
			sql.append(" and time BETWEEN ").append(start).append(" and ").append(end);
		}
		
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
	
	/**
	 * 
	 * @param pagesize
	 * @param pageindex
	 * @param type
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
			int start = year*10000;
			int end = (year+1)*10000;
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
	
	public static void main(String[] args) {
		LoginHistory history = new LoginHistory();
		history.setDeviceId("fdafdafdaf");
		history.setLoginTime("2014-11-23");
		insertLoginHistory(history);
	}
	
}
