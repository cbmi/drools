/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.common;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.core.FactException;
import org.drools.core.FactHandle;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseConfiguration;
import org.drools.core.StatefulSession;
import org.drools.core.reteoo.EntryPointNode;
import org.drools.core.reteoo.Rete;
import org.drools.core.reteoo.ReteooBuilder;
import org.drools.core.reteoo.ReteooWorkingMemory;
import org.drools.core.rule.Package;
import org.drools.core.rule.TypeDeclaration;
import org.drools.core.spi.FactHandleFactory;
import org.drools.core.spi.PropagationContext;
import org.kie.api.definition.process.Process;
import org.kie.internal.utils.CompositeClassLoader;

public interface InternalRuleBase
    extends
        RuleBase {

    /**
     * @return the id
     */
    String getId();
    
    int nextWorkingMemoryCounter();

    FactHandleFactory newFactHandleFactory();
    
    FactHandleFactory newFactHandleFactory(int id, long counter) throws IOException ;

    Map<String, Class<?>> getGlobals();

    
    RuleBaseConfiguration getConfiguration();
    
    Package getPackage(String name);
    
    Map<String, Package> getPackagesMap();

    void disposeStatefulSession(StatefulSession statefulSession);
    
    void executeQueuedActions();
    
    ReteooBuilder getReteooBuilder();

    /**
     * Assert a fact object.
     * 
     * @param handle
     *            The handle.
     * @param object
     *            The fact.
     * @param workingMemory
     *            The working-memory.
     * 
     * @throws FactException
     *             If an error occurs while performing the assertion.
     */
    void assertObject(FactHandle handle,
                             Object object,
                             PropagationContext context,
                             InternalWorkingMemory workingMemory) throws FactException;

    /**
     * Retract a fact object.
     * 
     * @param handle
     *            The handle.
     * @param workingMemory
     *            The working-memory.
     * 
     * @throws FactException
     *             If an error occurs while performing the retraction.
     */
    void retractObject(FactHandle handle,
                              PropagationContext context,
                              ReteooWorkingMemory workingMemory) throws FactException;
 
    ClassLoader getRootClassLoader();
    
    Rete getRete();
    
    InternalWorkingMemory[] getWorkingMemories();
    
    Process getProcess(String id);
    
    Process[] getProcesses();
    
    /**
     * Returns true if clazz represents an Event class. False otherwise.
     *  
     * @param clazz
     * @return
     */
    boolean isEvent( Class<?> clazz );

    int getNodeCount();

    /**
     * Returns the type declaration associated to the given class
     *
     * @param clazz
     * @return
     */
    TypeDeclaration getTypeDeclaration(Class<?> clazz);

    /**
     * Returns a collection with all TypeDeclarations in this rulebase
     * 
     * @return
     */
    Collection<TypeDeclaration> getTypeDeclarations();

    /**
     * Creates and allocates a new partition ID for this rulebase
     * 
     * @return
     */
    RuleBasePartitionId createNewPartitionId();

    /**
     * Return the list of Partition IDs for this rulebase
     * @return
     */
    List<RuleBasePartitionId> getPartitionIds();
    
    /**
     * Acquires a read lock on the rulebase
     */
    void readLock();
    
    /**
     * Releases a read lock on the rulebase
     */
    void readUnlock();
    
    void registerAddedEntryNodeCache(EntryPointNode node);
    Set<EntryPointNode> getAddedEntryNodeCache();

    void registeRremovedEntryNodeCache(EntryPointNode node);
    Set<EntryPointNode> getRemovedEntryNodeCache();
}
