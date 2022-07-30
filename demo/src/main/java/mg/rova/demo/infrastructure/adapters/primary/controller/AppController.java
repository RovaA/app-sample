package mg.rova.demo.infrastructure.adapters.primary.controller;

import mg.rova.demo.domain.ports.primary.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mg.rova.demo.domain.entities.Todo;

@Controller
public class AppController {

	@Autowired
	protected TodoService todoService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/indexleaf")
	public ModelAndView indexLeaf() {
		return new ModelAndView("indexLeaf", "entities", todoService.findAll());
	}

	@GetMapping({ "/create", "/create/{id}" })
	public ModelAndView createForm(@PathVariable(required = false) Long id) {
		Todo todo = new Todo();
		if (id != null)
			todo = todoService.find(id);
		return new ModelAndView("form", "todo", todo);
	}

	@PostMapping("/create")
	public String createSubmit(@ModelAttribute Todo todo) {
		todoService.save(todo);
		return "redirect:/indexleaf";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam Long id) {
		todoService.delete(todoService.find(id));
		return "redirect:/indexleaf";
	}
}
