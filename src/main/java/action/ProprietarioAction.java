package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dao.ProprietarioDao;

import model.Proprietario;



public class ProprietarioAction extends Action{
	
	
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
	
		ProprietarioDao proprietarioDao = new ProprietarioDao();
		List<Proprietario> list = proprietarioDao.selectAll();
		request.setAttribute("list", list);
		return(mapping.findForward("listar"));
		//request.getRequestDispatcher("/viewproprietarios.jsp").forward(request, response);
		      }

public ActionForward create(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			//cadastrar o proprietario
			ProprietarioDao proprietarioDao = new ProprietarioDao();
			Proprietario p = new Proprietario();
			p.setCpf_cnpj(request.getParameter("cpf_cnpj"));
			p.setNome(request.getParameter("nome"));
			p.setEndereco(request.getParameter("endereco"));
			proprietarioDao.insert(p);
			//response.sendRedirect("index.jsp");
			return(mapping.findForward("criar"));
		//request.getRequestDispatcher("/viewproprietarios.jsp").forward(request, response);
		      }


public ActionForward ListById(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
		ProprietarioDao proprietarioDao = new ProprietarioDao();
		int id = Integer.parseInt(request.getParameter("id"));
		Proprietario proprietario = proprietarioDao.selectById(id);
		request.setAttribute("proprietario", proprietario);
		//request.getRequestDispatcher("/editform.jsp").forward(request, response);
		return(mapping.findForward("mostrarId"));
			
		      }


public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
		ProprietarioDao proprietarioDao = new ProprietarioDao();
		int id = Integer.parseInt(request.getParameter("id"));
		String cpf_cnpj = request.getParameter("cpf_cnpj");
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		Proprietario p = new Proprietario();
		p.setId(id);
		p.setCpf_cnpj(cpf_cnpj);
		p.setNome(nome);
		p.setEndereco(endereco);
		proprietarioDao.update(p);
		//response.sendRedirect("index.jsp");
		return(mapping.findForward("atualizar"));
			
		      }


public ActionForward delete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
		ProprietarioDao proprietarioDao = new ProprietarioDao();
		int id = Integer.parseInt(request.getParameter("id"));
		proprietarioDao.delete(id);
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		return(mapping.findForward("deletar"));
			
		      }

}

