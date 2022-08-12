package action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.ProprietarioDao;
import dao.VeiculoDao;
import model.Proprietario;
import model.Veiculo;



public class VeiculoAction extends Action {
	
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward redirecionar = null;
			String acao = request.getParameter("acao");
			if (acao.equalsIgnoreCase ("index")) {
				redirecionar = index (mapping, form, request, response);
			} else if (acao.equalsIgnoreCase ("criar")) {
				redirecionar = create (mapping, form, request, response);
			}else if (acao.equalsIgnoreCase("mostrarId")) {
				redirecionar = ListById (mapping, form, request, response);
			}else if (acao.equalsIgnoreCase("atualizar")) {
				redirecionar = update (mapping, form, request, response);
			}else if (acao.equalsIgnoreCase("deletar")) {
				redirecionar = delete (mapping, form, request, response);
			}
			
			return redirecionar;     
			      }
	
	
	
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			VeiculoDao veiculoDao = new VeiculoDao();
			List<Veiculo> list = veiculoDao.selectAll();
			request.setAttribute("list", list);
			//request.getRequestDispatcher("/ViewVeiculo.jsp").forward(request, response);
			return(mapping.findForward("listar"));
			
			      }
	
	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//cadastrar o veiculo
			VeiculoDao veiculoDao = new VeiculoDao();
			Veiculo v = new Veiculo();
			v.setPlaca(request.getParameter("placa"));
			v.setRenavam(request.getParameter("renavam"));
			
			Proprietario p = new Proprietario();
			p.setId(Integer.parseInt(request.getParameter("id_prop")));
			v.setProprietario(p);
			veiculoDao.insert(v);
			//response.sendRedirect("index.jsp");
			return(mapping.findForward("criar"));
				
			      }
	
	
	public ActionForward ListById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//-------------------------------------------------------------
			VeiculoDao veiculoDao = new VeiculoDao();
			int id = Integer.parseInt(request.getParameter("id"));
			Veiculo veiculo = veiculoDao.selectById(id);
			
			
			//----------------------------------------------------------------
			ProprietarioDao proprietarioDao = new ProprietarioDao();
			List<Proprietario> list = proprietarioDao.selectAll();
			
			
			//---------------------------------------------------------------
			request.setAttribute("veiculo", veiculo);
			request.setAttribute("list", list);
			//request.getRequestDispatcher("/EditVeiculo.jsp").forward(request, response);
			return(mapping.findForward("mostrarId"));
			
			      }
	
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			int id = Integer.parseInt(request.getParameter("id"));
			String placa = request.getParameter("placa");
			String renavam = request.getParameter("renavam");
			int id_prop = Integer.parseInt(request.getParameter("id_prop"));
			
			Veiculo v = new Veiculo();
			v.setId(id);
			v.setPlaca(placa);
			v.setRenavam(renavam);
			
			Proprietario p = new Proprietario();
			p.setId(id_prop);
			v.setProprietario(p);
			
			VeiculoDao veiculoDao = new VeiculoDao();
			veiculoDao.update(v);
			//response.sendRedirect("index.jsp");
			return(mapping.findForward("atualizar"));
			
			      }
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			VeiculoDao veiculoDao = new VeiculoDao();
			int id = Integer.parseInt(request.getParameter("id"));
			veiculoDao.delete(id);
			//request.getRequestDispatcher("/index.jsp").forward(request, response);
			return(mapping.findForward("deletar"));
			
			      }
}
