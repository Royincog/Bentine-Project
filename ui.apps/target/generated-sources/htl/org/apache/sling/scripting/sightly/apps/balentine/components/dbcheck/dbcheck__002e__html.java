/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.balentine.components.dbcheck;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class dbcheck__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_obj = null;
Collection var_collectionvar0_list_coerced$ = null;
Object _global_clientlib = null;
_global_obj = renderContext.call("use", com.balentine.core.models.GetFruitsModel.class.getName(), obj());
out.write("\n<h1>Hello DB</h1>\n<form>\n<div class=\"form-row align-items-center\">\n    <div class=\"col-auto my-1\">\n      <label class=\"mr-sm-2\" for=\"inlineFormCustomSelect\">Choose Fruit</label>\n      <select class=\"custom-select mr-sm-2\" id=\"inlineFormCustomSelect\">\n        <option selected>Choose...</option>\n        <option value=\"1\">One</option>\n        <option value=\"2\">Two</option>\n        <option value=\"3\">Three</option>\n      </select>\n    </div>\n</form>\n<table class=\"table table-striped table-dark\">\n  <thead>\n    <tr>\n      <th scope=\"col\">Fruit Name</th>\n      <th scope=\"col\">Fruit Color</th>\n    </tr>\n      <tbody>\n          ");
{
    Object var_collectionvar0 = renderContext.getObjectModel().resolveProperty(_global_obj, "fruitObjects");
    {
        long var_size1 = ((var_collectionvar0_list_coerced$ == null ? (var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0)) : var_collectionvar0_list_coerced$).size());
        {
            boolean var_notempty2 = (var_size1 > 0);
            if (var_notempty2) {
                {
                    long var_end5 = var_size1;
                    {
                        boolean var_validstartstepend6 = (((0 < var_size1) && true) && (var_end5 > 0));
                        if (var_validstartstepend6) {
                            if (var_collectionvar0_list_coerced$ == null) {
                                var_collectionvar0_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar0);
                            }
                            long var_index7 = 0;
                            for (Object item : var_collectionvar0_list_coerced$) {
                                {
                                    boolean var_traversal9 = (((var_index7 >= 0) && (var_index7 <= var_end5)) && true);
                                    if (var_traversal9) {
                                        out.write("<tr>\n              <td>");
                                        {
                                            Object var_10 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(item, "fruitName"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_10));
                                        }
                                        out.write("</td>\n              <td>");
                                        {
                                            Object var_11 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(item, "fruitColor"), "text");
                                            out.write(renderContext.getObjectModel().toString(var_11));
                                        }
                                        out.write("</td>\n    </tr>\n");
                                    }
                                }
                                var_index7++;
                            }
                        }
                    }
                }
            }
        }
    }
    var_collectionvar0_list_coerced$ = null;
}
out.write("\n    </tbody>\n  </thead>\n</table>\n ");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
out.write("\n    ");
{
    Object var_templatevar12 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "js");
    {
        String var_templateoptions13_field$_categories = "balentine.dbcheck";
        {
            java.util.Map var_templateoptions13 = obj().with("categories", var_templateoptions13_field$_categories);
            callUnit(out, renderContext, var_templatevar12, var_templateoptions13);
        }
    }
}
out.write(" \n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

