package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CurrencyDTO;
import exception.CurrencyAlreadyExistException;
import exception.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.CurrencyRepositoryImpl;
import service.CurrencyService;
import util.Util;

import java.io.IOException;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    CurrencyService service = new CurrencyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            List<CurrencyDTO> currencies = service.getAllCurrencies();
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(currencies));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (DatabaseException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("База данных недоступна");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String sign = req.getParameter("sign");

        if (!Util.isCurrencyValid(code, name, sign)){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Некорректно введена валюта");
        }
        try {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setCode(code);
            currencyDTO.setName(name);
            currencyDTO.setSign(sign);
            service.addCurrency(currencyDTO);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (CurrencyAlreadyExistException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().write("Валюта с таким кодом уже существует");
        }
        catch (DatabaseException e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("База данных недоступна");
        }
    }
}
