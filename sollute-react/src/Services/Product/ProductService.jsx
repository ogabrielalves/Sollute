import axios from 'axios';
import { notify } from '../../Components/Notify/Notify';

const urlBase = 'http://localhost:8080/empresas';
const headers = {
  'Content-Type': 'application/json'
};
class ProductService {

  async getProdutos() {
    return await axios.get(`${urlBase}/listar-produtos/55756157000133`, {
      headers: headers
    })
      .then(res => res.data)
      .catch((err) => {
        console.error(`Request Failed ${err}`);
      });
  }

  async postProdutos(obj) {
    return await axios.post(`${urlBase}/criar-produto/55756157000133`,
      obj
    )
      .then(res => {
        notify('Produto criado com sucesso!', 'sucess')
        return res.data
      })
      .catch((err) => {
        notify('Erro ao criar produto.', 'error')
        console.error(`Request Failed ${err}`);
      });
  }

  deleteProdutos({ codigo }) {
    axios.delete(`${urlBase}/deletar-produto/${codigo}`)
  }
}


export default ProductService;