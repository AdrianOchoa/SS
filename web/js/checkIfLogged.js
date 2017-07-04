/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var isLogged = request.getSession().getAttribute("logged");
if (isLogged === false) {
    window.location.href = "forbidden.jsp";
}