package maven;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.security.DomainCombiner;

import org.apache.maven.DefaultMaven;
import org.apache.maven.Maven;
import org.apache.maven.model.ModelBase;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.apache.maven.RepositoryUtils;
import org.apache.maven.bridge.MavenRepositorySystem;
import org.apache.maven.cli.CLIManager;
import org.apache.maven.cli.CliRequest;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.execution.BuildSummary;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.DefaultMavenExecutionRequestPopulator;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionListener;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.internal.aether.DefaultRepositorySystemSessionFactory;
import org.apache.maven.shared.utils.logging.MessageUtils;
import org.codehaus.plexus.ContainerConfiguration;
import org.codehaus.plexus.DefaultContainerConfiguration;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.logging.console.ConsoleLoggerManager;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.internal.impl.DefaultLocalRepositoryProvider;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleOne {
	
	class ExecutionContext {
		
		String _mavenHomeDirectory;
		
		String _workingDirectory;
		
		String _multiModuleProjectDirectory;
		
		MavenExecutionRequest _mavenExecutionRequest;
		
		public void init( MavenExecutionRequest req ) {
			
		}
		
		public void setMavenHomeDirectory( String dir ) {
			_mavenHomeDirectory = dir;
		}
		
		public void setWorkingDirectory( String dir ) {
			_workingDirectory = dir;
		}

		public void setMultiModuleProjectDirectory( String dir ) {
			_multiModuleProjectDirectory = dir;
		}
		
		public void setMavenExecutionRequest( MavenExecutionRequest req ) {
			_mavenExecutionRequest = req;
			
			_mavenExecutionRequest
				.setMultiModuleProjectDirectory( new File( _multiModuleProjectDirectory ) );
			
			// usually null ExecutionListener listener = req.getExecutionListener();
			req.setExecutionListener( _listener );
		}
		
		ExecutionListener _listener = new ExecutionListener() {
			
			@Override
			public void sessionStarted(ExecutionEvent event) {
				System.out.println( "sessionStarted" );
			}
			
			@Override
			public void sessionEnded(ExecutionEvent event) {
				System.out.println( "sessionEnded" );
			}
			
			@Override
			public void projectSucceeded(ExecutionEvent event) {
				System.out.println( "projectSucceeded" );
			}
			
			@Override
			public void projectStarted(ExecutionEvent event) {
				System.out.println( "projectStarted" );
			}
			
			@Override
			public void projectSkipped(ExecutionEvent event) {
				System.out.println( "projectSkipped" );
			}
			
			@Override
			public void projectFailed(ExecutionEvent event) {
				System.out.println( "projectFailed" );
			}
			
			@Override
			public void projectDiscoveryStarted(ExecutionEvent event) {
				System.out.println( "projectDiscoveryStarted" );
			}
			
			@Override
			public void mojoSucceeded(ExecutionEvent event) {
				System.out.println( "mojoSucceeded" );
			}
			
			@Override
			public void mojoStarted(ExecutionEvent event) {
				System.out.println( "mojoStarted" );
			}
			
			@Override
			public void mojoSkipped(ExecutionEvent event) {
				System.out.println( "mojoSkipped" );
			}
			
			@Override
			public void mojoFailed(ExecutionEvent event) {
				System.out.println( "mojoFailed" );
			}
			
			@Override
			public void forkedProjectSucceeded(ExecutionEvent event) {
				System.out.println( "forkedProjectSucceeded" );
			}
			
			@Override
			public void forkedProjectStarted(ExecutionEvent event) {
				System.out.println( "forkedProjectStarted" );
			}
			
			@Override
			public void forkedProjectFailed(ExecutionEvent event) {
				System.out.println( "forkedProjectFailed" );
			}
			
			@Override
			public void forkSucceeded(ExecutionEvent event) {
				System.out.println( "forkSucceeded" );
			}
			
			@Override
			public void forkStarted(ExecutionEvent event) {
				System.out.println( "forkStarted" );
			}
			
			@Override
			public void forkFailed(ExecutionEvent event) {
				System.out.println( "projectSucceeded" );
			}
		}; 
	
	};

	class MavenManaged extends MavenCli {
		
		ExecutionContext _context;
		
		public ExecutionContext setExecutionContext( ExecutionContext ctx ) {
			ExecutionContext prev_ctx = _context;
			_context = ctx;
			return prev_ctx;
		}
		
		public int doMain( CliRequest cliRequest ) {
			MavenExecutionRequest request = cliRequest.getRequest();
			
			_context.setMavenExecutionRequest( request );
			
			return super.doMain( cliRequest );
		}
	}
	
	public static final String MULTIMODULE_PROJECT_DIRECTORY = "maven.multiModuleProjectDirectory";

	@Test
	public void test() {
		//fail("Not yet implemented");
		
//		//org.apache.maven.classrealm.ClassRealmManager man = new ClassRealmManager();
//		MavenExecutionRequest req = null;// new DefaultMavenExecutionRequest();
//		
//		// create a new container
//        try {
//			PlexusContainer container = new DefaultPlexusContainer();
//			
//			Maven mvn = container.lookup( Maven.class );
//			
//			req = new DefaultMavenExecutionRequest();
//			
//			mvn.execute( req );
//			
//		} catch (PlexusContainerException e1) {
//			e1.printStackTrace();
//		} catch (ComponentLookupException e) {
//			e.printStackTrace();
//		}
//		
//		ModelBase m = new ModelBase();
//		//m.getDependencies()
//		DefaultMaven maven = new DefaultMaven();
//		
//		//DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
//		//LocalRepositoryProvider local_provider = new DefaultLocalRepositoryProvider();
//		//ArtifactRepository ar = new DefaultLocalRepositoryProvider()
//		//req.setLocalRepository( )
//		try {
//			req.setLocalRepositoryPath( File.createTempFile( "mvnTmp", "" ) );
//			
//			//req.setBaseDirectory( File.createTempFile( "mvnTmp", "" ) );
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//DefaultRepositorySystemSessionFactory session_factory = new DefaultRepositorySystemSessionFactory();
//		//RepositorySystemSession session = session_factory.newRepositorySession( req );
//		//session.getWorkspaceReader()
//		
//		MavenExecutionResult res = maven.execute( req );
//		BuildSummary build_sum = res.getBuildSummary( res.getProject() );
//		long time = build_sum.getTime();
		//build_sum.getProject().
		
		//CLIManager manager = new CLIManager();
		//manager.displayHelp( System.out );
		
		//MavenCli cli = new MavenCli();

		//MessageUtils.systemInstall();
		
		// MavenCli.doMain( "clean".split(" "), null );// File.createTempFile( "mvnTmp", "" ).toString() );
//		new MavenCli().doMain( ("archetype:generate -DgroupId=test.org -DartifactId=one"
//				+ " -DarchetypeArtifactId=maven-archetype-quickstart -Dmaven.multiModuleProjectDirectory=~/.m2").split(" "),
//				"/tmp/simple", System.out, System.err );
		
		//System.setProperty( "maven.home", new File( "./" ).getAbsolutePath() );
		// /opt/apache/maven-3.5.4/bin/m2.conf
		System.setProperty( "maven.home", "/tmp/diff_cp/first/target/" );
		//System.setProperty( "maven.home", "/opt/apache/maven-3.5.4/lib/" );
		
		//if ( cliRequest.workingDirectory == null )
	    //if ( cliRequest.multiModuleProjectDirectory == null )
		///ExecutionContext ctx = new ExecutionContext();
		///ctx.setMavenHomeDirectory( "/opt/apache/maven-3.5.4/bin/" );
		///ctx.setWorkingDirectory( "/tmp/" );
		///ctx.setMultiModuleProjectDirectory( "/opt/apache/maven-3.5.4/" );
		
		///MavenManaged mm = new MavenManaged();
		///mm.setExecutionContext( ctx );
		//mm.doMain( ("-vh -X archetype:generate").split(" "), "/tmp/", System.out, System.err );
	
		//System.setProperty( "maven.conf",  "/tmp/diff_cp/first/target" );
		
//		exec "$JAVACMD" \
//		  $MAVEN_OPTS \
//		  $MAVEN_DEBUG_OPTS \
//		  -classpath "${CLASSWORLDS_JAR}" \
//		  "-Dclassworlds.conf=${MAVEN_HOME}/bin/m2.conf" \
//		  "-Dmaven.home=${MAVEN_HOME}" \
//		  "-Dlibrary.jansi.path=${MAVEN_HOME}/lib/jansi-native" \
//		  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
//		  ${CLASSWORLDS_LAUNCHER} "$@"
		
		//CLASSWORLDS_JAR=`echo "${MAVEN_HOME}"/boot/plexus-classworlds-*.jar`
		//CLASSWORLDS_LAUNCHER=org.codehaus.plexus.classworlds.launcher.Launcher
		
		// http://maven.apache.org/docs/3.3.1/release-notes.html
		// defining ${maven.projectBasedir}/.mvn/maven.config file 
		// which contains the configuration options for the command line. 
		// For example things like -T3 -U --fail-at-end. 
		// So you only have to call maven just by using 
		// mvn clean package instead of mvn -T3 -U --fail-at-end clean package
		String basedirProperty = "/tmp/diff_cp/first/target"; //"/opt/apache/maven-3.5.4/";
		//basedirProperty = "/opt/apache/maven-3.5.4/";
		System.setProperty( MULTIMODULE_PROJECT_DIRECTORY, basedirProperty );

		// Make sure the Maven home directory is an absolute path 
		// to save us from confusion with say drive-relative Windows paths.
		//String mavenHome = "/tmp/diff_cp/first/target/lib/"; //"/opt/apache/maven-3.5.4/bin/";
		//System.setProperty( "maven.home", mavenHome );

		String [] args = { "-X" };
		args = new String[]{ "clean package" };// -Dclassworlds.conf=/tmp/diff_cp/first/target/m2.conf" };
		//.doMain( ("\"-vh -X archetype:generate\"").split(" "), "/tmp/", System.out, System.err );
		//.doMain( ("\"-X archetype:generate\"").split(" "), "/tmp/", System.out, System.err );
		
		//new MavenCli().doMain( args, "/tmp", System.out, System.err );
		//new MavenCli().doMain( args, "/tmp/diff_cp/first/target ", System.out, System.err );
		
		start( args );
//		com.google.inject.ProvisionException: Unable to provision, see the following errors:
//
//			1) No implementation for org.apache.maven.classrealm.ClassRealmManager was bound.
//			  while locating org.apache.maven.project.DefaultProjectBuildingHelper
		
		String gg = "asdfsdf";
		System.out.println( gg );
		
		//org.apache.maven.repository.RepositorySystem;
		// <groupId>org.apache.maven</groupId> <artifactId>maven-compat</artifactId>
		//WARNING: Error injecting: org.apache.maven.repository.legacy.LegacyRepositorySystem
		//java.lang.NoClassDefFoundError: org/sonatype/aether/RepositorySystemSession
		// use org.eclipse.aether.aether-api
		// see http://maven.apache.org/aether.html
		// use org.apache.maven.resolver.maven-resolver-api
		// see
		// https://codehaus-plexus.github.io/plexus-classworlds/
	     
		//MessageUtils.systemUninstall();
	}
	
	// http://www.javased.com/?api=org.codehaus.plexus.DefaultPlexusContainer
	// http://www.javased.com/index.php?source_dir=legacy-maven-support/maven3-interceptor/src/main/java/org/jvnet/hudson/maven3/launcher/Maven3Launcher.java
	static void start( String [] args) {
		ClassLoader orig = Thread.currentThread().getContextClassLoader();

		ClassWorld world = new ClassWorld( "plexus.core", orig );
		ClassRealm containerRealm = new ClassRealm( world, "maven", orig );
		try {
			//ClassRealm containerRealm=(ClassRealm)Thread.currentThread().getContextClassLoader();
		    ContainerConfiguration cc = new DefaultContainerConfiguration().setName("maven").setRealm(containerRealm);
		    DefaultPlexusContainer container = new DefaultPlexusContainer(cc);
		    container.setLoggerManager( new ConsoleLoggerManager() );
		    
		    //Maven maven=(Maven)container.lookup("org.apache.maven.Maven","default");
		    Maven maven = container.lookup( Maven.class );//, "default");
		    
		    MavenExecutionRequest request = getMavenExecutionRequest(args,container);
		    MavenExecutionResult result = maven.execute(request);
		    
		    System.out.println( result );
		    //hudsonMavenExecutionResult = new HudsonMavenExecutionResult(result);
		    //return 0;
		  }
		 catch (  ComponentLookupException e) {
		    System.out.println( e.getMessage() );
		    e.printStackTrace();
		    
		  } catch (PlexusContainerException e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			
		} finally {
		    Thread.currentThread().setContextClassLoader(orig);
		}
	}
	
	static MavenExecutionRequest getMavenExecutionRequest( String[] args, DefaultPlexusContainer container ) throws Exception { 
        //MavenExecutionRequestBuilder mavenExecutionRequestBuilder = container.lookup( MavenExecutionRequestBuilder.class );
		MavenExecutionRequest request = new DefaultMavenExecutionRequest();
		//MavenRepositorySystem.buildArtifactRepository(repo) 
		DefaultMavenExecutionRequestPopulator populator = new DefaultMavenExecutionRequestPopulator( null );
		populator.populateDefaults(request);
		
        //MavenExecutionRequest request = mavenExecutionRequestBuilder.getMavenExecutionRequest( args, System.out ); 
        //if ( mavenExecutionListener != null ) { 
        //    request.setExecutionListener( mavenExecutionListener ); 
        //} 
        return request; 
    } 

}
