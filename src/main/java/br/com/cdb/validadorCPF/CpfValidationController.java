package br.com.cdb.validadorCPF;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/receita")
public class CpfValidationController {

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Map<String, Object>> consultarCpf(@PathVariable String cpf) {
		Map<String, Object> response = new HashMap<>();
		response.put("cpf", cpf);
		
		boolean ativo = cpf.startsWith("0") || cpf.startsWith("1") || cpf.startsWith("2") || cpf.startsWith("3") || cpf.startsWith("4") || cpf.startsWith("5");
		String status = ativo ? "ATIVO" : "INATIVO";
		
		if (cpf.startsWith("8")) {
		    throw new RuntimeException("Instabilidade simulada na Receita");
		}
		
		if (validarCpf(cpf)) {
			response.put("success", true);
			response.put("valid", true);
			response.put("status", status);
			response.put("message", "CPF válido");
			return ResponseEntity.ok(response);
		} else {
			response.put("success", true);
			response.put("valid", false);
			response.put("message", "CPF inválido ou não existente");
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	private boolean validarCpf(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			return false;
		}
		
		//remover caracteres nao numericos
		cpf = cpf.replaceAll("[^0-9]", "");
		
		//verificar se tem 11 dígitos ou se todos são iguais
		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			return false;
		}
		
		try {
			//calcular primeiro dígito verificador
			int soma = 0;
			for (int i = 0; i < 9; i++) {
				soma += (10 - i) * Character.getNumericValue(cpf.charAt(i));
			}
			int digito1 = 11 - (soma % 11);
			if (digito1 > 9)
				digito1 = 0;
			
			//calcular segundo dígito verificador
			soma = 0;
			for (int i = 0; i < 10; i++) {
				soma += (11 - i) * Character.getNumericValue(cpf.charAt(i));
			}
			int digito2 = 11 - (soma % 11);
			if (digito2 > 9)
				digito2 = 0;
			
			//verificar os dígitos
			return (digito1 == Character.getNumericValue(cpf.charAt(9)))
					&& (digito2 == Character.getNumericValue(cpf.charAt(10)));
		} catch(NumberFormatException | IndexOutOfBoundsException e) {
			return false;
		}
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
	    Map<String, Object> erro = new HashMap<>();
	    erro.put("message", ex.getMessage());
	    erro.put("valid", null);
	    erro.put("success", false);

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

}
