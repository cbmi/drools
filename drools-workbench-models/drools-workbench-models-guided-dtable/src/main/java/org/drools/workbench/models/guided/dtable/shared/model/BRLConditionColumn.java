/*
 * Copyright 2011 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.drools.workbench.models.guided.dtable.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.drools.workbench.models.commons.shared.rule.IPattern;

/**
 * A Condition column defined with a BRL fragment
 */
public class BRLConditionColumn extends ConditionCol52
        implements
        BRLColumn<IPattern, BRLConditionVariableColumn> {

    private static final long serialVersionUID = 540l;

    private List<IPattern> definition = new ArrayList<IPattern>();

    private List<BRLConditionVariableColumn> childColumns = new ArrayList<BRLConditionVariableColumn>();

    public List<IPattern> getDefinition() {
        return this.definition;
    }

    public void setDefinition( List<IPattern> definition ) {
        this.definition = definition;
    }

    public List<BRLConditionVariableColumn> getChildColumns() {
        return this.childColumns;
    }

    public void setChildColumns( List<BRLConditionVariableColumn> childColumns ) {
        this.childColumns = childColumns;
    }

    @Override
    public void setHeader( String header ) {
        super.setHeader( header );
        for ( BRLConditionVariableColumn variable : this.childColumns ) {
            variable.setHeader( header );
        }
    }

    @Override
    public void setHideColumn( boolean hideColumn ) {
        super.setHideColumn( hideColumn );
        for ( BRLConditionVariableColumn variable : this.childColumns ) {
            variable.setHideColumn( hideColumn );
        }
    }

}
