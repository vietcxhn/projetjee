package mybootapp.web;

import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.model.Product;
import mybootapp.model.ProductCode;
import mybootapp.repo.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepository repo;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        Product p1 = new Product();
        p1.setName("Car");
        p1.setPrice(2000.0);
        p1.setDescription("byeSmall car");
        p1.setType("x");
        Product p2 = new Product();
        p2.setName("Gift");
        p2.setPrice(100.0);
        p2.setDescription("byeBig gift");
        p2.setType("x");
        repo.save(p1);
        repo.save(p2);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProducts() {
        logger.info("List of products");
        Collection<Product> products = repo.findAll();
        return new ModelAndView("productsList", "products", products);
    }

    @ModelAttribute("products")
    Collection<Product> products() {
        logger.info("Making list of products");
        return repo.findAll();
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@ModelAttribute Product p) {
        return "productForm";
    }
    
    @ModelAttribute
    public Product newProduct(
        @RequestParam(value = "id", required = false) Long productNumber)
    {
        if (productNumber != null) {
            logger.info("find product " + productNumber);
            var p = repo.findById(productNumber);
            return p.get();
        }
        Product p = new Product();
        p.setNumber(null);
        p.setName("");
        p.setPrice(0.0);
        p.setDescription("");
        logger.info("new product = " + p);
        return p;
    }
    
    @Autowired
    ProductValidator validator;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute @Valid Product p, BindingResult result) {
        validator.validate(p, result);
        if (result.hasErrors()) {
            return "productForm";
        }
        repo.save(p);
        return "redirect:list";
    }

    
    @ModelAttribute("productTypes")
    public Map<String, String> productTypes() {
        Map<String, String> types = new LinkedHashMap<>();
        types.put("type1", "Type 1");
        types.put("type2", "Type 2");
        types.put("type3", "Type 3");
        types.put("type4", "Type 4");
        types.put("type5", "Type 5");
        return types;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(ProductCode.class, new ProductCodeEditor());
    }
}