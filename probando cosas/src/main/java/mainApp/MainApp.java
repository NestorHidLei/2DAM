package mainApp;



import java.util.List;

import org.hibernate.query.Query;

import model.Categorias;
import utils.HibernateUtil;

public class MainApp {
		
	public static void main(String[] args) {
//		String hql = "Select c FROM Categorias c";
//		Query<Categorias> query = HibernateUtil.getSession().createQuery(hql, Categorias.class);
//		
//		List<Categorias> categorias = query.getResultList();
//		
//		for (Categorias categoria : categorias) {
//			System.out.println(categoria.toString());
//		}
//		
//		HibernateUtil.closeSession();
		
		String hql = "Select c.categoria FROM Categorias c";
		Query<String> query = HibernateUtil.getSession().createQuery(hql, String.class);
		
		List<String> categorias = query.list();
		
		for (String categoria : categorias) {
			System.out.println(categoria);
		}
		
		HibernateUtil.closeSession();
	}

}
